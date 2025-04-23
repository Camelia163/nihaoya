<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="''">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
	
		<div class="news-preview-pv">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="search-item">
					<el-input v-model="title" placeholder="标题"></el-input>
				</el-form-item>
				<el-button class="search-btn" type="primary" @click="getNewsList(1)">
					<span class="icon iconfont icon-chakan14"></span>
					搜索
				</el-button>
			</el-form>
			
			<!-- category -->
			<div class="category-list">
				<div class="item" @click="categoryClick(0)" :class="categoryIndex == 0 ? 'active' : ''">全部</div>
				<div v-for="(item,index) in categoryList" @click="categoryClick(index+1)" :key="index" class="item" :class="categoryIndex == index+1 ? 'active' : ''">{{item.typename}}</div>
			</div>
			<div class="list9 index-pv1">
				<div v-for="(item,index) in newsList" :key="index" @click="toNewsDetail(item)" class="list-item animation-box">
					<img class="image" :src="baseUrl + item.picture" >
					<div class="infoBox">
						<div class="name">{{item.title}}</div>
						<div class="desc">{{item.introduction}}</div>
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{item.addtime.split(' ')[0]}}</span>
						</div>
						<div class="publisher_item">
							<span class="icon iconfont icon-geren16"></span>
							<span class="label">发布人：</span>
							<span class="text">{{item.name}}</span>
						</div>
						<div class="like_item">
							<span class="icon iconfont icon-zan10"></span>
							<span class="label">点赞：</span>
							<span class="text">{{item.thumbsupnum}}</span>
						</div>
						<div class="collect_item">
							<span class="icon iconfont icon-shoucang10"></span>
							<span class="label">收藏：</span>
							<span class="text">{{item.storeupnum}}</span>
						</div>
						<div class="view_item">
							<span class="icon iconfont icon-chakan2"></span>
							<span class="label">浏览次数：</span>
							<span class="text">{{item.clicknum}}</span>
						</div>
						<div class="tags">查看详情</div>
					</div>
				</div>
			</div>
		
			<el-pagination
				background
				id="pagination" class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				:page-sizes="pageSizes"
				prev-text="上一页"
				next-text="下一页"
				:hide-on-single-page="false"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>

			<!-- 热门信息 -->
			<div class="hot">
				<div class="hot-title">热门信息</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toNewsDetail(item)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				baseUrl: this.$config.baseUrl,
				breadcrumbItem: [
				  {
					name: '公告信息'
				  }
				],
				newsList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				layouts: '',
				title: '',
				categoryIndex: 0,
				categoryList: [],
				hotList: [],
			}
		},
		created() {
			this.getCategoryList()
			
			this.getHotList()
		},
		watch:{
			$route(newValue){
				this.getCategoryList()
			}
		},
		//方法集合
		methods: {
			getCategoryList(){
				this.$http.get('newstype/list', {params: {sort: 'typename',order: 'desc'}}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data.list;
						if(this.$route.query.homeFenlei){
							for(let i=0;i<this.categoryList.length;i++) {
								if(this.$route.query.homeFenlei == this.categoryList[i].typename) {
									this.categoryIndex = i + 1;
									const currentRoute = this.$route;
									const routeWithoutQuery = { ...currentRoute };
									delete routeWithoutQuery.query;
									this.$router.replace(routeWithoutQuery)
									break;
								}
							}
						}
						this.getNewsList(1);
					}
				});
			},
			categoryClick(index) {
				this.categoryIndex = index
				this.getNewsList()
			},
			getNewsList(page) {
				let params = {page, limit: this.pageSize,sort:'addtime',order:'desc'};
				let searchWhere = {};
				if(this.title != '') searchWhere.title = '%' + this.title + '%';
				if(this.categoryIndex!=0){
					searchWhere.typename = this.categoryList[this.categoryIndex - 1].typename
				}
				this.$http.get('news/list', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
			curChange(page) {
				this.getNewsList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getNewsList(1);
			},
			prevClick(page) {
				this.getNewsList(page);
			},
			nextClick(page) {
				this.getNewsList(page);
			},
			toNewsDetail(item) {
				this.$router.push({path: '/index/newsDetail', query: {id: item.id}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.news-preview-pv {
				padding: 0 calc((100% - 1300px)/2);
				margin: 0px auto;
				color: #333;
				background: #fff;
				display: block;
				width: 100%;
				font-size: 16px;
				justify-content: flex-start;
				align-items: flex-start;
				position: relative;
				flex-wrap: wrap;
				.list-form-pv {
						padding: 10px;
						margin: 0;
						background: none;
						display: flex;
						width: 101%;
						justify-content: center;
						align-items: center;
						flex-wrap: wrap;
						height: auto;
						order: 0;
						.search-item {
								margin: 0 0px;
								.el-input {
										width: 100%;
									}
				.el-input /deep/ .el-input__inner {
										border: 1px solid #ccc;
										border-radius: 4px;
										padding: 0 10px;
										margin: 0 10px 0 0;
										color: #333;
										background: #fff;
										width: auto;
										font-size: 16px;
										line-height: 42px;
										min-width: 350px;
										height: 42px;
									}
			}
			.search-btn {
								cursor: pointer;
								border: 0;
								border-radius: 8px;
								padding: 0px 20px;
								margin: 0 10px 0 0;
								color: #fff;
								background: rgb(0, 78, 162);
								width: auto;
								font-size: inherit;
								line-height: 42px;
								height: 42px;
								.icon {
										margin: 0 3px 0 0;
										color: #fff;
										font-size: inherit;
									}
			}
		}
		.category-list {
						border: 1px solid rgb(45, 50, 64);
						padding: 10px 20px;
						margin: 20px 0 0;
						background: rgb(244, 244, 244);
						display: flex;
						width: 101%;
						flex-wrap: wrap;
						height: auto;
						order: 3;
						.item {
								cursor: pointer;
								border: 0px solid #b3b3b3;
								padding: 0px 20px;
								margin: 0;
								color: rgb(45, 50, 64);
								display: flex;
								font-size: 16px;
								line-height: 60px;
								flex-wrap: wrap;
								border-radius: 4px;
								background: none;
								justify-content: center;
								align-items: center;
								min-width: 120px;
							}
			
			.item:hover {
								color: rgb(0, 78, 162);
								background: none;
								border-color: #3397da;
							}
			
			.item.active {
								color: rgb(0, 78, 162);
								background: none;
								border-color: #3397da;
							}
		}
		.list9 {
						padding: 0;
						margin: 20px 0 0;
						color: #ddd;
						background: #fff;
						display: flex;
						gap: 20px;
						width: 100%;
						font-size: 12px;
						justify-content: flex-start;/**我要修改了，原来是space-between**/
						flex-wrap: wrap;
						height: auto;
						.list-item {
								margin: 0;
								overflow: hidden;
								background: #fff;
								width: 420px;
								position: relative;
								height: 400px;
								img {
										object-fit: cover;
										display: block;
										width: 100%;
										transition: 0.3s;
										height: 100%;
									}
				.infoBox {
										padding: 10px;
										left: 0;
										bottom: -100%;
										background: rgba(0,0,0,.5);
										width: 100%;
										line-height: 1.8;
										position: absolute;
										transition: 0.3s;
										height: 100%;
										.name {
												margin: 0 0 10px;
												overflow: hidden;
												color: #fff;
												white-space: nowrap;
												width: 100%;
												font-size: 15px;
												line-height: 32px;
												text-overflow: ellipsis;
											}
					.desc {
												margin: 0 0 10px;
												overflow: hidden;
												color: #eee;
												font-size: 13px;
												line-height: 24px;
												height: 96px;
											}
					.time_item {
												padding: 0 10px 0 0;
												display: inline-block;
												.icon {
														margin: 0 2px 0 0;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														line-height: 1.5;
													}
						.text {
														line-height: 1.5;
													}
					}
					.publisher_item {
												padding: 0 10px 0 0;
												display: inline-block;
												.icon {
														margin: 0 2px 0 0;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														line-height: 1.5;
													}
						.text {
														line-height: 1.5;
													}
					}
					.like_item {
												padding: 0 10px 0 0;
												display: inline-block;
												.icon {
														margin: 0 2px 0 0;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														line-height: 1.5;
													}
						.text {
														line-height: 1.5;
													}
					}
					.collect_item {
												padding: 0 10px 0 0;
												display: inline-block;
												.icon {
														margin: 0 2px 0 0;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														line-height: 1.5;
													}
						.text {
														line-height: 1.5;
													}
					}
					.view_item {
												padding: 0;
												display: inline-block;
												.icon {
														margin: 0 2px 0 0;
														font-size: inherit;
														line-height: 1.5;
													}
						.label {
														line-height: 1.5;
													}
						.text {
														line-height: 1.5;
													}
					}
					.tags {
												cursor: pointer;
												padding: 0 10px;
												margin: 20px 0 0;
												color: #fff;
												background: rgb(95, 136, 176);
												display: block;
												width: 120px;
												font-size: 12px;
												line-height: 32px;
												text-align: center;
											}
				}
			}
			.list-item:hover {
				img {
										transform: scale(1.1);
									}
				.infoBox {
										cursor: pointer;
										bottom: 0;
									}
			}
		}
		.hot {
						margin: 30px 40px 0 0;
						background: none;
						width: 100%;
						height: auto;
						order: 50;
						.hot-title {
								padding: 0;
								margin: 0;
								color: rgb(0, 78, 162);
								background: url(https://codegen.caihongy.cn/20250204/d150dc26e9f44cb0b06dfc34989fcba3.png) no-repeat center bottom;
								width: 100%;
								font-size: 26px;
								line-height: 60px;
								position: relative;
								text-align: center;
								height: 60px;
							}
			.hot-list {
								padding: 20px 0;
								margin: 0;
								background: #fff;
								display: flex;
								gap: 20px;
								width: 100%;
								flex-wrap: wrap;
								height: auto;
								.hot-item {
										cursor: pointer;
										border: 1px solid rgb(45, 50, 64);
										border-radius: 4px;
										padding: 20px;
										margin: 0;
										background: none;
										display: flex;
										width: 420px;
										align-items: center;
										text-align: center;
										height: auto;
										img {
												border-radius: 4px;
												flex-shrink: 0;
												margin: 0 auto;
												object-fit: cover;
												display: block;
												width: 190px;
												height: 100px;
											}
					.hot-name {
												padding: 0px 10px;
												overflow: hidden;
												color: #333;
												white-space: nowrap;
												width: calc(100% - 0px);
												font-size: 15px;
												line-height: 40px;
												text-overflow: ellipsis;
												text-align: left;
											}
					.hot-time {
												padding: 0 5px;
												color: #666;
												display: none;
												width: calc(100% - 0px);
												font-size: 15px;
												line-height: 24px;
												text-align: center;
											}
				}
			}
		}
	}
	
	.index-pv1 .animation-box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	
	.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
	}
	
	.index-pv1 .animation-box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
	}
	
	.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(0.98) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
</style>
