<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
			>
			<el-form-item class="add-item" label="岗位名称" prop="gangweimingcheng">
				<el-select  @change="gangweimingchengChange" v-model="ruleForm.gangweimingcheng" placeholder="请选择岗位名称">
					<el-option
						v-for="(item,index) in gangweimingchengOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="图片" v-if="type!='cross' || (type=='cross' && !ro.tupian)" prop="tupian">
				<file-upload
					tip="点击上传图片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.tupian?ruleForm.tupian:''"
					@change="tupianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="图片" prop="tupian">
				<img v-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="时薪" prop="shixin">
				<el-input-number v-model="ruleForm.shixin" placeholder="时薪" :disabled=" false ||ro.shixin"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="时长/小时" prop="shizhang">
				<el-input-number v-model="ruleForm.shizhang" placeholder="时长/小时" :disabled=" false ||ro.shizhang"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="总工资" prop="zonggongzi">
				<el-input v-model="zonggongzi" placeholder="总工资" disabled></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="录入时间" prop="lurushijian">
				<el-date-picker
					:disabled=" false  ||ro.lurushijian"
					format="yyyy 年 MM 月 dd 日"
					value-format="yyyy-MM-dd"
					v-model="ruleForm.lurushijian" 
					type="date"
					placeholder="录入时间">
				</el-date-picker> 
			</el-form-item>
			<el-form-item class="add-item" label="单位账号" prop="danweizhanghao">
				<el-input v-model="ruleForm.danweizhanghao" 
					placeholder="单位账号" clearable :disabled=" false  ||ro.danweizhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="单位名称" prop="danweimingcheng">
				<el-input v-model="ruleForm.danweimingcheng" 
					placeholder="单位名称" clearable :disabled=" false  ||ro.danweimingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="学生账号" prop="xueshengzhanghao">
				<el-select  @change="xueshengzhanghaoChange" v-model="ruleForm.xueshengzhanghao" placeholder="请选择学生账号">
					<el-option
						v-for="(item,index) in xueshengzhanghaoOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="学生姓名" prop="xueshengxingming">
				<el-input v-model="ruleForm.xueshengxingming" 
					placeholder="学生姓名" clearable :disabled=" false  ||ro.xueshengxingming"></el-input>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont "></span>
					<span class="text">提交</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont "></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
				ro:{
					gangweimingcheng : false,
					tupian : false,
					shixin : false,
					shizhang : false,
					zonggongzi : false,
					lurushijian : false,
					danweizhanghao : false,
					danweimingcheng : false,
					xueshengzhanghao : false,
					xueshengxingming : false,
					sfsh : false,
					shhf : false,
					ispay : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					gangweimingcheng: '',
					tupian: '',
					shixin: '',
					shizhang: '',
					zonggongzi: '',
					lurushijian: '',
					danweizhanghao: '',
					danweimingcheng: '',
					xueshengzhanghao: '',
					xueshengxingming: '',
				},
				gangweimingchengOptions: [],
				xueshengzhanghaoOptions: [],


				rules: {
					gangweimingcheng: [
					],
					tupian: [
					],
					shixin: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					shizhang: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					zonggongzi: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					lurushijian: [
					],
					danweizhanghao: [
					],
					danweimingcheng: [
					],
					xueshengzhanghao: [
					],
					xueshengxingming: [
					],
					sfsh: [
					],
					shhf: [
					],
					ispay: [
					],
				},
				centerType: false,
			};
		},
		computed: {


			zonggongzi:{
				get: function () {
					return 1*this.ruleForm.shixin*this.ruleForm.shizhang
				}
			},

		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
			this.ruleForm.lurushijian = this.getCurDate()
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o=='gangweimingcheng'){
							this.ruleForm.gangweimingcheng = obj[o];
							this.ro.gangweimingcheng = true;
							continue;
						}
						if(o=='tupian'){
							this.ruleForm.tupian = obj[o]?obj[o].split(",")[0]:'';
							this.ro.tupian = true;
							continue;
						}
						if(o=='shixin'){
							this.ruleForm.shixin = obj[o];
							this.ro.shixin = true;
							continue;
						}
						if(o=='shizhang'){
							this.ruleForm.shizhang = obj[o];
							this.ro.shizhang = true;
							continue;
						}
						if(o=='zonggongzi'){
							this.ruleForm.zonggongzi = obj[o];
							this.ro.zonggongzi = true;
							continue;
						}
						if(o=='lurushijian'){
							this.ruleForm.lurushijian = obj[o];
							this.ro.lurushijian = true;
							continue;
						}
						if(o=='danweizhanghao'){
							this.ruleForm.danweizhanghao = obj[o];
							this.ro.danweizhanghao = true;
							continue;
						}
						if(o=='danweimingcheng'){
							this.ruleForm.danweimingcheng = obj[o];
							this.ro.danweimingcheng = true;
							continue;
						}
						if(o=='xueshengzhanghao'){
							this.ruleForm.xueshengzhanghao = obj[o];
							this.ro.xueshengzhanghao = true;
							continue;
						}
						if(o=='xueshengxingming'){
							this.ruleForm.xueshengxingming = obj[o];
							this.ro.xueshengxingming = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						if((json.danweizhanghao!=''&&json.danweizhanghao) || json.danweizhanghao==0){
							this.ruleForm.danweizhanghao = json.danweizhanghao;
							this.ro.danweizhanghao = true;
						}
						if((json.danweimingcheng!=''&&json.danweimingcheng) || json.danweimingcheng==0){
							this.ruleForm.danweimingcheng = json.danweimingcheng;
							this.ro.danweimingcheng = true;
						}
					}
				});
				this.$http.get('option/gangweixinxi/gangweimingcheng', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.gangweimingchengOptions = res.data.data;
					}
				});
				this.$http.get('option/xuesheng/xueshengzhanghao', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.xueshengzhanghaoOptions = res.data.data;
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},
			// 下二随
			gangweimingchengChange () {
				this.$http.get('follow/gangweixinxi/gangweimingcheng?columnValue=' + this.ruleForm.gangweimingcheng, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						if(res.data.data.shixin){
							this.ruleForm.shixin = res.data.data.shixin
						}
						if(res.data.data.xueshengxingming){
							this.ruleForm.xueshengxingming = res.data.data.xueshengxingming
						}
					}
				});
			},
			// 下二随
			xueshengzhanghaoChange () {
				this.$http.get('follow/xuesheng/xueshengzhanghao?columnValue=' + this.ruleForm.xueshengzhanghao, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						if(res.data.data.shixin){
							this.ruleForm.shixin = res.data.data.shixin
						}
						if(res.data.data.xueshengxingming){
							this.ruleForm.xueshengxingming = res.data.data.xueshengxingming
						}
					}
				});
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`gongzifafang/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				this.ruleForm.zonggongzi = this.zonggongzi
				if(!this.ruleForm.id) {
					delete this.ruleForm.userid
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}


						await this.$http.post(`gongzifafang/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.$router.go(-1);
			},
			tupianUploadChange(fileUrls) {
				this.ruleForm.tupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 0 calc((100% - 1200px)/2) 20px;
		margin: 0px auto;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 16px;
		position: relative;
		.add-update-form {
			border: 1px solid #24355950;
			padding: 40px 20px;
			margin: 0px auto;
			background: #fff;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				border-radius: 0px;
				padding: 6px 0 0;
				margin: 0 0 20px 0;
				background: none;
				border-color: #475a8310;
				border-width:  0 0 0px;
				border-style: solid;
				/deep/ .el-form-item__label {
					padding: 0 10px 0 0;
					color: #333;
					font-weight: 600;
					width: 180px;
					font-size: inherit;
					line-height: 40px;
					text-align: right;
				}
				/deep/ .el-form-item__content {
					margin-left: 180px;
				}
				.el-input {
					width: auto;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: auto;
				}
				.el-select /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 10px;
					color: inherit;
					width: 100%;
					font-size: 16px;
					min-width: inherit !important;
					height: 40px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor {
					width: auto;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				/deep/ .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				/deep/ .upload .upload-img {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 4px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 4px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 4px;
					color: #999;
					background: #fff;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				/deep/ .el-upload__tip {
					color: #888;
					font-size: 16px;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 48%;
					height: auto;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 0px solid #ddd;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 50%;
					height: auto;
				}
				/deep/ .el-input__inner::placeholder {
					color: inherit;
					font-size: inherit;
				}
				/deep/ textarea::placeholder {
					color: inherit;
					font-size: inherit;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: none;
					margin: 0;
					width: 100%;
					min-height: 350px;
					border-color: #ccc;
					border-width: 1px;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					object-fit: cover;
					width: 100px;
					height: 100px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 20px;
					margin: 0;
					color: #fff;
					background: #0674fc60;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 34px;
					height: 34px;
				}
				.viewBtn:hover {
				}
				.unviewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #333;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: #eee;
					width: auto;
					height: 34px;
				}
				.unviewBtn:hover {
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 20px 0;
				text-align: center;
				.submitBtn {
					border: 0;
					cursor: pointer;
					padding: 0 24px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 0px;
					background: rgb(0, 78, 162);
					width: auto;
					text-align: center;
					min-width: 120px;
					height: 40px;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.submitBtn:hover {
					.icon {
					}
					.text {
					}
				}
				.closeBtn {
					border: 0px solid #ddd;
					cursor: pointer;
					padding: 0 24px;
					margin: 0 20px 0 0;
					color: #fff;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 0px;
					background: rgb(232, 232, 232);
					width: auto;
					text-align: center;
					min-width: 120px;
					height: 40px;
					.icon {
						color: #fff;
					}
					.text {
						color: rgb(110, 110, 110);
					}
				}
				.closeBtn:hover {
					.icon {
					}
					.text {
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
