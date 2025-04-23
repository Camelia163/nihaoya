<template>
	<div class="home-preview">



		<!-- 关于我们 -->
		<div id="about" class="animate__animated">
			<div class="about_item">
				<div class="about_title_box">
					<div class="about_title">{{aboutUsDetail.title}}</div>
					<div class="about_subtitle">{{aboutUsDetail.subtitle}}</div>
				</div>
				<div class="about_img">
					<img :src="baseUrl + aboutUsDetail.picture1">
					<img :src="baseUrl + aboutUsDetail.picture2">
					<img :src="baseUrl + aboutUsDetail.picture3">
				</div>
				<div class="about_content ql-snow ql-editor" v-html="aboutUsDetail.content"></div>
				<div class="about_idea1" />
				<div class="about_idea2" />
				<div class="about_idea3" />
				<div class="about_idea4" />
				<div class="about_more" @click="toDetail('aboutusDetail',aboutUsDetail)">
					<span>查看更多</span>
					<span class="icon iconfont icon-jiantou25"></span>
				</div>
			</div>
		</div>
		<!-- 关于我们 -->

		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">公告信息</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<div class="list list26 index-pv1">
				<div class="list-body-top">
					<div v-if="newsList.length>0" class="list-item1" @click="toDetail('newsDetail', newsList[0])">
						<div class="img"><img :src="baseUrl + newsList[0].picture" /></div>
						<div class="name">{{newsList[0].title}}</div>
					</div>
					<div class="list-body-right">
						<div v-if="newsList.length>1" class="list-item2" @click="toDetail('newsDetail', newsList[1])">
							<div class="img"><img :src="baseUrl + newsList[1].picture" /></div>
							<div class="news_info">
								<div class="name">{{newsList[1].title}}</div>
								<div class="desc">{{newsList[1].introduction}}</div>
								<span class="time">{{newsList[1].addtime.split(' ')[0]}}</span>
							</div>
						</div>
						<div v-if="newsList.length>2" class="list-item2" @click="toDetail('newsDetail', newsList[2])">
							<div class="img"><img :src="baseUrl + newsList[2].picture" /></div>
							<div class="news_info">
								<div class="name">{{newsList[2].title}}</div>
								<div class="desc">{{newsList[2].introduction}}</div>
								<span class="time">{{newsList[2].addtime.split(' ')[0]}}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="list-body-bottom">
					<div v-for="(item,index) in newsList" v-if="index>2" :key="index" class="list-item3" @click="toDetail('newsDetail', item)">
						<div class="name">{{item.title}}</div>
						<span class="time">{{item.addtime.split(' ')[0]}}</span>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 岗位推荐 -->
		<div id="animate_recommendgangweixinxi" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">岗位信息推荐</span>
				<span class="recommend_subhead">{{'gangweixinxi'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div v-if="gangweixinxiRecommend.length" class="list list11 index-pv1">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in gangweixinxiRecommend" :key="index" @click="toDetail('gangweixinxiDetail', item)">
						<img :name="item.id" v-if="preHttp(item.tupian)" :src="item.tupian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" alt="" />
						<div class="name">{{item.gangweimingcheng}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('gangweixinxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 岗位推荐 -->
		<!-- 特价岗位 -->
		<div id="animate_listgangweixinxi" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">岗位信息展示</span>
				<span class="list_subhead">{{'gangweixinxi'.toUpperCase()}} SHOW</span>
			</div>
			<div class="list list18 index-pv1">
				<div class="list-body" v-if="gangweixinxiList.length">
					<div class="list-body-left">
						<div class="list-item1" @click="toDetail('gangweixinxiDetail', gangweixinxiList[0])">
							<div class="infoBox">
								<div class="name">{{gangweixinxiList[0].gangweimingcheng}}</div>
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{gangweixinxiList[0].addtime.split(' ')[0]}}</span>
								</div>
								<div class="publisher_item">
									<span class="icon iconfont icon-geren16"></span>
									<span class="label">发布人：</span>
									<span class="text">{{gangweixinxiList[0].danweizhanghao}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏：</span>
									<span class="text">{{gangweixinxiList[0].storeupnum}}</span>
								</div>
								<div class="view_item">
									<span class="icon iconfont icon-chakan2"></span>
									<span class="label">浏览次数：</span>
									<span class="text">{{gangweixinxiList[0].clicknum}}</span>
								</div>
							</div>
							<div class="img">
								<img v-if="preHttp(gangweixinxiList[0].tupian)" :src="gangweixinxiList[0].tupian.split(',')[0]" alt="" />
								<img v-else :src="baseUrl + (gangweixinxiList[0].tupian?gangweixinxiList[0].tupian.split(',')[0]:'')" alt="" />
							</div>
						</div>
						<div class="list-item2" @click="toDetail('gangweixinxiDetail', gangweixinxiList[1])">
							<div class="img">
								<img v-if="preHttp(gangweixinxiList[1].tupian)" :src="gangweixinxiList[1].tupian.split(',')[0]" alt="" />
								<img v-else :src="baseUrl + (gangweixinxiList[1].tupian?gangweixinxiList[1].tupian.split(',')[0]:'')" alt="" />
							</div>
							<div class="infoBox">
								<div class="name">{{gangweixinxiList[1].gangweimingcheng}}</div>
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{gangweixinxiList[1].addtime.split(' ')[0]}}</span>
								</div>
								<div class="publisher_item">
									<span class="icon iconfont icon-geren16"></span>
									<span class="label">发布人：</span>
									<span class="text">{{gangweixinxiList[1].danweizhanghao}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏：</span>
									<span class="text">{{gangweixinxiList[1].storeupnum}}</span>
								</div>
								<div class="view_item">
									<span class="icon iconfont icon-chakan2"></span>
									<span class="label">浏览次数：</span>
									<span class="text">{{gangweixinxiList[1].clicknum}}</span>
								</div>
							</div>
						</div>
					</div>
					<div class="list-body-right">
						<div class="swiper-container listSwiper18 list-list-18gangweixinxi">
							<div class="swiper-wrapper">
								<div class="swiper-slide" v-for="(item,index) in gangweixinxiList" :key="index" v-if="index>2">
									<div class="list-item" @click="toDetail('gangweixinxiDetail', item)">
										<div class="img">
											<img v-if="preHttp(item.tupian)" :src="item.tupian.split(',')[0]" alt="" />
											<img v-else :src="baseUrl + (item.tupian?item.tupian.split(',')[0]:'')" alt="" />
										</div>
										<div class="infoBox">
											<div class="name">{{item.gangweimingcheng}}</div>
											<div class="time_item">
												<span class="icon iconfont icon-shijian21"></span>
												<span class="label">发布时间：</span>
												<span class="text">{{item.addtime.split(' ')[0]}}</span>
											</div>
											<div class="publisher_item">
												<span class="icon iconfont icon-geren16"></span>
												<span class="label">发布人：</span>
												<span class="text">{{item.danweizhanghao}}</span>
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
										</div>
									</div>
								</div>
							</div>
							<div class="swiper-button-prev"></div>
							<div class="swiper-button-next"></div>
							<div class="swiper-pagination"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('gangweixinxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价岗位 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				aboutUsDetail: {},
				newsList: [],
				gangweixinxiRecommend: [],

				gangweixinxiList: [],



				listListSwiper18gangweixinxi: null,

			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getAboutUs();
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			swiperChanges() {
				if (this['listListSwiper18gangweixinxi']) this['listListSwiper18gangweixinxi'].destroy()
				setTimeout(()=>{
					this['listListSwiper18gangweixinxi'] = new Swiper(".list-list-18gangweixinxi", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"pagination":{"el":".swiper-pagination","clickable":true},"loop":true,"coverflowEffect":{"rotate":10,"stretch":10,"depth":60,"modifier":3,"slideShadows":false},"centeredSlides":true,"slidesPerView":1,"spaceBetween":0,"autoplay":{"delay":3000,"disableOnInteraction":false}})
				},750)
			},
			recommendIndexClick11(index, name) {
				this['recommendIndex11' + name] = index[this['recommendColumn11' + name]]
				this.getList()
			},

			listIndexClick18(index, name) {
				this['listIndex18' + name] = index[this['listColumn18' + name]]
				this.getList()
			},

			handleScroll() {
				let arr = [
					{id:'about',css:'animate__fadeInRightBig'},
					{id:'system',css:'animate__slideInUp'},
					{id:'animate_recommendgangweixinxi',css:'animate__zoomIn'},
					{id:'animate_listgangweixinxi',css:'animate__bounceInLeft'},
					{id:'animate_newsnews',css:'animate__lightSpeedInLeft'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getAboutUs() {
				this.$http.get('aboutus/detail/1', {}).then(res => {
					if(res.data.code == 0) {
						this.aboutUsDetail = res.data.data;
					}
				})
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 9,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "gangweixinxi/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "gangweixinxi/autoSort2";
				}
				data = {
					page: 1,
					limit: 8,
				}
				data['sfsh'] = '是'
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.gangweixinxiRecommend = res.data.data.list;
					}
				});
			
				data = {
					page: 1,
					limit: 5,
				}
				data['sfsh']= '是'

				this.$http.get('gangweixinxi/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.gangweixinxiList = res.data.data.list;
					}
				});
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0px auto;
		flex-direction: column;
		background: #fff;
		display: flex;
		width: 100%;
		#about {
			padding: 30px 0 0;
			margin: 0 auto;
			background: url() no-repeat center top / 100% 100%;
			width: 100%;
			position: relative;
			height: auto;
			order: 3;
			.about_item {
				padding: 0;
				margin: 40px auto 20px;
				z-index: 9;
				overflow: hidden;
				align-content: flex-start;
				background: none;
				display: flex;
				width: 1300px;
				flex-wrap: wrap;
				height: auto;
				.about_title_box {
					padding: 0;
					margin: 0 0 30px;
					background: url(http://codegen.caihongy.cn/20250204/d150dc26e9f44cb0b06dfc34989fcba3.png) no-repeat left bottom / 100% auto;
					width: 100%;
					text-align: center;
					.about_title {
						margin: 0 auto;
						color: #000;
						background: none;
						font-weight: 600;
						display: inline-block;
						width: auto;
						font-size: 24px;
						line-height: 60px;
						text-align: center;
					}
					.about_subtitle {
						margin: 0;
						color: #999;
						display: none;
						width: 100%;
						font-size: 18px;
						line-height: 40px;
						text-align: center;
					}
				}
				.about_img {
					padding: 0;
					margin: 0;
					z-index: 999;
					display: flex;
					right: 0;
					flex-wrap: wrap;
					border-radius: 10px;
					overflow: hidden;
					top: 0;
					background: none;
					width: 49%;
					height: 320px;
					order: 2;
					img:nth-child(1) {
						margin: 0;
						object-fit: cover;
						display: block;
						width: 100%;
						height: 100%;
					}
					img:nth-child(2) {
						margin: 0;
						object-fit: cover;
						display: none;
						width: 34%;
						height: 100%;
					}
					img:nth-child(3) {
						margin: 0;
						object-fit: cover;
						display: none;
						width: 33%;
						height: 100%;
					}
				}
				.about_content {
					padding: 0;
					margin: 0px 2% 0 0;
					overflow: hidden;
					color: #333;
					background: none;
					width: 49%;
					font-size: 16px;
					line-height: 30px;
					height: 210px;
				}
				.about_idea1 {
					z-index: -1;
					top: 0;
					background: #d10602;
					display: none;
					width: 50%;
					position: absolute;
					right: 0;
					height: 400px;
				}
				.about_idea2 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_idea3 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_idea4 {
					background: url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat;
					display: none;
					width: 285px;
					height: 100px;
				}
				.about_more {
					border: 0px solid #999;
					border-radius: 0px;
					padding: 0 20px;
					margin: 40px 0 0;
					background: rgb(0, 78, 162);
					display: block;
					width: 230px;
					line-height: 50px;
					text-align: center;
					order: 3;
					span:nth-child(1) {
						color: #fff;
						font-size: 18px;
					}
					span:nth-child(2) {
						color: #fff;
						display: none;
						font-size: 18px;
					}
				}
				.about_more:hover {
					cursor: pointer;
					opacity: 1;
				}
			}
		}
		.news {
			padding: 30px 0 0;
			margin: 0;
			background: url(http://codegen.caihongy.cn/20250204/e1ab17d2c4e54904a2dafd4b6f04d842.png) no-repeat center top;
			width: 100%;
			position: relative;
			text-align: center;
			order: 4;
			.news_title_box {
				padding: 0;
				margin: 0 auto;
				background: url(http://codegen.caihongy.cn/20250204/d150dc26e9f44cb0b06dfc34989fcba3.png) no-repeat center bottom;
				width: 1200px;
				position: relative;
				text-align: center;
				height: 60px;
				.news_title {
					margin: 0 auto;
					color: rgb(0, 78, 162);
					background: none;
					font-weight: 600;
					display: block;
					letter-spacing: 1px;
					width: auto;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
				}
				.news_subhead {
					margin: 0;
					color: #999;
					display: none;
					width: 100%;
					font-size: 18px;
					line-height: 40px;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list26 {
				border-radius: 10px;
				padding: 40px 20px 100px;
				margin: 30px auto 0;
				overflow: hidden;
				background: rgb(244, 244, 244);
				width: 1300px;
				.list-body-top {
					margin: 0px 10px 0;
					overflow: hidden;
					display: flex;
					width: calc(100% - 20px);
					justify-content: space-between;
					align-items: flex-start;
					height: auto;
					.list-item1 {
						cursor: pointer;
						border-radius: 10px;
						overflow: hidden;
						width: 560px;
						height: auto;
						.img {
							overflow: hidden;
							width: 100%;
							height: 400px;
							img {
								transform: scale(1);
								object-fit: cover;
								width: 100%;
								transition: all 1s ease 0s;
								height: 100%;
							}
						}
						.name {
							overflow: hidden;
							color: #fff;
							white-space: nowrap;
							background: rgb(45, 50, 64);
							width: 100%;
							font-size: 15px;
							line-height: 60px;
							text-overflow: ellipsis;
							transition: all 0.3s ease 0s;
							text-align: center;
							height: 100px;
						}
					}
					.list-item1:hover {
						.img {
							img {
								transform: scale(1.05);
								transition: all 1s ease 0s;
							}
						}
						.name {
							color: #333;
							background: #c7cfe180;
						}
					}
					.list-body-right {
						overflow: hidden;
						flex-direction: column;
						display: flex;
						gap: 20px;
						width: 640px;
						justify-content: space-between;
						.list-item2 {
							cursor: pointer;
							border-radius: 10px;
							padding: 20px;
							background: #fff;
							display: flex;
							width: 100%;
							align-items: center;
							transition: all 0.3s ease 0s;
							height: auto;
							.img {
								flex-shrink: 0;
								width: 300px;
								height: 200px;
								img {
									transform: scale(1);
									object-fit: cover;
									width: 100%;
									transition: all 1s ease 0s;
									height: 100%;
								}
							}
							.news_info {
								margin: 0 0 0 20px;
								.name {
									overflow: hidden;
									color: #333;
									white-space: nowrap;
									font-weight: 600;
									width: 100%;
									font-size: 15px;
									line-height: 30px;
									text-overflow: ellipsis;
									transition: all 0.3s ease 0s;
									text-align: left;
									height: 30px;
								}
								.desc {
									margin: 10px 0;
									overflow: hidden;
									color: #555;
									width: 100%;
									font-size: 15px;
									line-height: 24px;
									transition: all 0.3s ease 0s;
									height: 48px;
								}
								.time {
									color: #666;
									font-size: 15px;
									float: right;
									transition: all 0.3s ease 0s;
								}
							}
						}
						.list-item2:hover {
							background: #c7cfe180;
							.img {
								img {
									transform: scale(1.05);
								}
							}
							.news_info {
								.name {
									color: #000;
								}
								.desc {
									color: #333;
								}
								.time {
									color: #666;
								}
							}
						}
					}
				}
				.list-body-bottom {
					padding: 0;
					margin: 20px 0 0;
					display: flex;
					width: 100%;
					flex-wrap: wrap;
					height: auto;
					.list-item3 {
						cursor: pointer;
						padding: 0 10px;
						margin: 0 10px 20px;
						background: #fff;
						display: flex;
						width: calc(33.33% - 20px);
						justify-content: space-between;
						transition: all 0.3s ease 0s;
						height: 60px;
						.name {
							padding: 0 0 0 10px;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							width: calc(100% - 100px);
							font-size: 15px;
							line-height: 60px;
							text-overflow: ellipsis;
							transition: all 0.3s ease 0s;
							text-align: left;
							height: 60px;
						}
						.time {
							color: #666;
							width: 90px;
							font-size: 15px;
							line-height: 60px;
							transition: all 0.3s ease 0s;
							text-align: right;
						}
					}
					.list-item3:hover {
						background: #c7cfe180;
						.name {
							color: #000;
						}
						.time {
							color: #666;
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #999;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 20px;
				margin: 20px auto 40px;
				top: -110px;
				background: rgb(0, 78, 162);
				display: inline-block;
				width: 250px;
				line-height: 48px;
				position: relative;
				text-align: center;
				.text {
					color: #fff;
					font-size: 16px;
				}
				.icon {
					color: #333;
					display: none;
					font-size: 15px;
				}
			}
		}
		.recommend {
			padding: 0 0 10px;
			margin: 0px auto;
			background: none;
			width: 100%;
			position: relative;
			text-align: center;
			order: 1;
			.recommend_title_box {
				padding: 0;
				margin: 0 auto;
				background: url(http://codegen.caihongy.cn/20250204/d150dc26e9f44cb0b06dfc34989fcba3.png) no-repeat center bottom;
				width: 1300px;
				position: relative;
				text-align: center;
				height: 60px;
				.recommend_title {
					margin: 0 auto;
					color: rgb(0, 78, 162);
					background: none;
					font-weight: 600;
					display: block;
					letter-spacing: 1px;
					width: auto;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
				}
				.recommend_subhead {
					margin: 0;
					color: #999;
					display: none;
					width: 100%;
					font-size: 18px;
					line-height: 40px;
					text-align: center;
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
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list11 {
				padding: 0;
				margin: 0 auto;
				background: #fff;
				display: block;
				width: 1300px;
				justify-content: space-between;
				height: auto;
				.tab_view {
					margin: 30px 0 0 0;
					background: rgb(244, 244, 244);
					display: flex;
					width: 100%;
					height: auto;
					.tab {
						cursor: pointer;
						padding: 20px 0;
						margin: 0;
						width: 100%;
						border-color: #efefef;
						border-width: 0 0 1px 0;
						border-style: solid;
						height: auto;
						.icon {
							margin: 0 5px 0 0;
							color: #333;
							font-size: 16px;
							line-height: 44px;
						}
						img {
							margin: 0 5px 0 0;
							object-fit: cover;
							display: inline-block;
							width: 44px;
							height: 44px;
						}
						.text {
							color: rgb(0, 78, 162);
							font-size: 16px;
							line-height: 44px;
						}
					}
					.more_btn {
						cursor: pointer;
						padding: 10px 20px;
						margin: 0;
						display: none;
						width: 100%;
						border-color: #efefef;
						border-width: 0 0 1px 0;
						border-style: solid;
						height: auto;
						.icon {
							margin: 0 5px 0 0;
							color: #333;
							font-size: 16px;
							line-height: 44px;
						}
						.text {
							color: #333;
							font-size: 16px;
							line-height: 44px;
						}
					}
				}
				.list-body {
					margin: 50px 0 0;
					background: #fff;
					flex: 1;
					display: flex;
					gap: 20px;
					width: auto;
					flex-wrap: wrap;
					height: auto;
					.list-item {
						cursor: pointer;
						background: rgb(244, 244, 244);
						width: 420px;
						position: relative;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100%;
							height: 200px;
						}
						.name {
							padding: 0 20px;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							width: 100%;
							font-size: 16px;
							line-height: 30px;
							text-overflow: ellipsis;
							text-align: left;
						}
						.price {
							padding: 0 20px;
							color: #f00;
							width: 100%;
							font-size: 16px;
							line-height: 2.5;
							text-align: left;
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #999;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 20px;
				margin: 30px auto;
				background: rgb(0, 78, 162);
				display: inline-block;
				width: 250px;
				line-height: 48px;
				text-align: center;
				.text {
					color: #fff;
					font-size: 16px;
				}
				.icon {
					color: #fff;
					display: none;
					font-size: 15px;
				}
			}
		}
		.lists {
			padding: 0;
			margin: 30px 0;
			background: url(http://codegen.caihongy.cn/20250204/b9d0a2ad832c48d990695fd44955cb97.png) no-repeat center bottom;
			width: 100%;
			position: relative;
			text-align: center;
			order: 2;
			.list_title_box {
				padding: 0;
				margin: 0;
				background: url(http://codegen.caihongy.cn/20250204/d150dc26e9f44cb0b06dfc34989fcba3.png) no-repeat center bottom;
				width: 100%;
				position: relative;
				text-align: center;
				height: 60px;
				.list_title {
					margin: 0 auto;
					color: rgb(0, 78, 162);
					background: none;
					font-weight: 600;
					display: block;
					letter-spacing: 1px;
					width: auto;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
				}
				.list_subhead {
					margin: 0;
					color: #999;
					display: none;
					width: 100%;
					font-size: 18px;
					line-height: 40px;
					text-align: center;
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
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list18 {
				margin: 0 auto;
				color: #333;
				background: none;
				font-weight: 500;
				width: 1300px;
				font-size: 14px;
				.tab_view {
					margin: 10px auto;
					display: none;
					width: 60%;
					justify-content: center;
					.tab {
						cursor: pointer;
						border-radius: 5px;
						margin: 0 5px 10px;
						color: #333;
						background: #eee;
						display: block;
						width: 140px;
						font-size: 14px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
					.tab:hover {
						color: #ddd;
						background: #0195df;
					}
					.tab.tabActive {
						color: #fff;
						background: #5555ff;
					}
				}
				.list-body {
					padding: 0;
					margin: 30px 0 0;
					display: flex;
					width: 100%;
					.list-body-left {
						margin: 0 auto 0 0;
						width: 59%;
						.list-item1 {
							border: 1px solid rgb(45, 50, 64);
							border-radius: 10px;
							margin: 0 0 20px;
							overflow: hidden;
							display: flex;
							width: 100%;
							align-items: center;
							transition: all 0s;
							.infoBox {
								border: 1px solid #eee;
								cursor: pointer;
								padding: 12px;
								background: #fff;
								width: 50%;
								height: 225px;
								.name {
									overflow: hidden;
									color: #000;
									white-space: nowrap;
									font-weight: 600;
									width: 100%;
									font-size: 15px;
									line-height: 1.5;
									text-overflow: ellipsis;
								}
								.price {
									padding: 5px 0;
									color: #f00;
									font-size: 16px;
									line-height: 1.5;
								}
								.time_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
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
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
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
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										color: #000;
										font-size: 12px;
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
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
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
										line-height: 1.5;
									}
									.label {
										line-height: 1.5;
									}
									.text {
										line-height: 1.5;
									}
								}
							}
							.img {
								overflow: hidden;
								width: 50%;
								transition: all .3s;
								height: 225px;
								img {
									transform: scale(1);
									object-fit: cover;
									display: block;
									width: 100%;
									transition: all .3s;
									height: 100%;
								}
							}
						}
						.list-item1:hover {
							.infoBox {
								background: #fff;
								.name {
								}
								.price {
								}
								.time_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.publisher_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.like_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.collect_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.view_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
							}
							.img {
								cursor: pointer;
								overflow: hidden;
								img {
									transform: scale(1.2);
								}
							}
						}
						.list-item2 {
							border: 1px solid rgb(45, 50, 64);
							border-radius: 10px;
							overflow: hidden;
							display: flex;
							width: 100%;
							align-items: center;
							transition: all 0s;
							.img {
								overflow: hidden;
								width: 50%;
								transition: all .3s;
								height: 225px;
								img {
									transform: scale(1);
									object-fit: cover;
									display: block;
									width: 100%;
									transition: all .3s;
									height: 100%;
								}
							}
							.infoBox {
								border: 1px solid #eee;
								cursor: pointer;
								padding: 12px;
								background: #fff;
								width: 50%;
								height: 225px;
								.name {
									overflow: hidden;
									color: #000;
									white-space: nowrap;
									font-weight: 600;
									width: 100%;
									font-size: 15px;
									line-height: 1.5;
									text-overflow: ellipsis;
								}
								.price {
									padding: 5px 0;
									color: #f00;
									font-size: 16px;
									line-height: 1.5;
								}
								.time_item {
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
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
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
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
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
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
									padding: 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
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
										line-height: 1.5;
									}
									.label {
										line-height: 1.5;
									}
									.text {
										line-height: 1.5;
									}
								}
							}
						}
						.list-item2:hover {
							transform: translate3d(0px, 0px, 0px);
							.img {
								cursor: pointer;
								overflow: hidden;
								img {
									transform: scale(1.2);
								}
							}
							.infoBox {
								background: #fff;
								.name {
								}
								.price {
								}
								.time_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.publisher_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.like_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.collect_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
								.view_item {
									.icon {
									}
									.label {
									}
									.text {
									}
								}
							}
						}
					}
					.list-body-right {
						width: 39%;
						.list-item {
							cursor: pointer;
							width: 100%;
							position: relative;
							.img {
								width: 100%;
								height: 470px;
								img {
									transform: scale(1);
									object-fit: cover;
									display: block;
									width: 100%;
									transition: all .3s;
									height: 100%;
								}
							}
							.infoBox {
								padding: 12px 12px 38px;
								color: #eee;
								left: 0;
								bottom: 0;
								background: rgba(0, 0, 0, .3);
								width: 100%;
								position: absolute;
								.name {
									overflow: hidden;
									color: #fff;
									white-space: nowrap;
									font-weight: 600;
									width: 100%;
									font-size: 15px;
									line-height: 1.5;
									text-overflow: ellipsis;
								}
								.price {
									padding: 10px;
									color: #f00;
									font-size: 16px;
									line-height: 1.5;
								}
								.time_item {
									padding: 0 10px 0 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
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
									padding: 0 10px 0 0;
									display: inline-block;
									.icon {
										margin: 0 2px 0 0;
										line-height: 1.5;
									}
									.label {
										line-height: 1.5;
									}
									.text {
										line-height: 1.5;
									}
								}
							}
						}
						.swiper-button-prev {
							left: 10px;
							right: auto;
						}
						.swiper-button-prev::after {
							color: #d7b830;
						}
						.swiper-button-next {
							left: auto;
							right: 10px;
						}
						.swiper-button-next::after {
							color: #d7b830;
						}
						/deep/.swiper-pagination {
							transform: translate3d(0,0,0);
							z-index: 10;
							left: 0;
							bottom: 10px;
							width: 100%;
							position: absolute;
							transition: .3s opacity;
							text-align: center;
							.swiper-pagination-bullet {
								cursor: pointer;
								border-radius: 100%;
								margin: 0 4px;
								background: #eee;
								display: inline-block;
								width: 8px;
								opacity: .8;
								height: 8px;
							}
							.swiper-pagination-bullet:hover {
								background: #f7db61;
							}
							.swiper-pagination-bullet.swiper-pagination-bullet-active {
								background: #f7db61;
							}
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #999;
				cursor: pointer;
				border-radius: 0;
				padding: 0 20px;
				margin: 30px auto 40px;
				background: rgb(0, 78, 162);
				display: inline-block;
				width: 250px;
				line-height: 48px;
				text-align: center;
				.text {
					color: #fff;
					font-size: 16px;
				}
				.icon {
					color: #fff;
					font-size: 16px;
				}
			}
		}
	}
</style>
