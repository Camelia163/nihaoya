<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
			>
			<el-form-item class="add-item" label="岗位编号" prop="gangweibianhao">
				<el-input v-model="ruleForm.gangweibianhao" placeholder="岗位编号" disabled></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="岗位名称" prop="gangweimingcheng">
				<el-input v-model="ruleForm.gangweimingcheng" 
					placeholder="岗位名称" clearable :disabled=" false  ||ro.gangweimingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="岗位类型" prop="gangweileixing">
				<el-select v-model="ruleForm.gangweileixing" placeholder="请选择岗位类型" :disabled=" false  ||ro.gangweileixing" >
					<el-option
						v-for="(item,index) in gangweileixingOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item"  label="专业" prop="zhuanye">
				<el-select v-model="ruleForm.zhuanye" placeholder="请选择专业" :disabled=" false  ||ro.zhuanye" >
					<el-option
						v-for="(item,index) in zhuanyeOptions"
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
			<el-form-item class="add-item"  label="状态" prop="zhuangtai">
				<el-select v-model="ruleForm.zhuangtai" placeholder="请选择状态" :disabled=" false  ||ro.zhuangtai" >
					<el-option
						v-for="(item,index) in zhuangtaiOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="招聘人数" prop="zhaopinrenshu">
				<el-input v-model.number="ruleForm.zhaopinrenshu" 
					placeholder="招聘人数" clearable :disabled=" false  ||ro.zhaopinrenshu"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="时薪/小时" prop="shixin">
				<el-input-number v-model="ruleForm.shixin" placeholder="时薪/小时" :disabled=" false ||ro.shixin"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="截止日期" prop="jiezhiriqi">
				<el-date-picker
					:disabled=" false  ||ro.jiezhiriqi"
					format="yyyy 年 MM 月 dd 日"
					value-format="yyyy-MM-dd"
					v-model="ruleForm.jiezhiriqi" 
					type="date"
					placeholder="截止日期">
				</el-date-picker> 
			</el-form-item>
			<el-form-item class="add-item" label="单位名称" prop="danweimingcheng">
				<el-input v-model="ruleForm.danweimingcheng" 
					placeholder="单位名称" clearable :disabled=" false  ||ro.danweimingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="单位账号" prop="danweizhanghao">
				<el-input v-model="ruleForm.danweizhanghao" 
					placeholder="单位账号" clearable :disabled=" false  ||ro.danweizhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="岗位要求" prop="gangweiyaoqiu">
				<editor 
					v-model="ruleForm.gangweiyaoqiu" 
					class="editor" 
					myQuillEditor="gangweiyaoqiu"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item class="add-item" label="岗位简介" prop="gangweijianjie">
				<editor 
					v-model="ruleForm.gangweijianjie" 
					class="editor" 
					myQuillEditor="gangweijianjie"
					action="file/upload">
				</editor>
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
					gangweibianhao : false,
					gangweimingcheng : false,
					gangweileixing : false,
					zhuanye : false,
					tupian : false,
					zhuangtai : false,
					zhaopinrenshu : false,
					gangweiyaoqiu : false,
					shixin : false,
					jiezhiriqi : false,
					gangweijianjie : false,
					danweimingcheng : false,
					danweizhanghao : false,
					sfsh : false,
					shhf : false,
					clicktime : false,
					clicknum : false,
					storeupnum : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					gangweibianhao: this.getUUID(),
					gangweimingcheng: '',
					gangweileixing: '',
					zhuanye: '',
					tupian: '',
					zhuangtai: '',
					zhaopinrenshu: '',
					gangweiyaoqiu: '',
					shixin: '',
					jiezhiriqi: '',
					gangweijianjie: '',
					danweimingcheng: '',
					danweizhanghao: '',
					clicktime: '',
					clicknum: '',
					storeupnum: '',
				},
				gangweileixingOptions: [],
				zhuanyeOptions: [],
				zhuangtaiOptions: [],


				rules: {
					gangweibianhao: [
					],
					gangweimingcheng: [
					],
					gangweileixing: [
					],
					zhuanye: [
					],
					tupian: [
					],
					zhuangtai: [
					],
					zhaopinrenshu: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					gangweiyaoqiu: [
					],
					shixin: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					jiezhiriqi: [
					],
					gangweijianjie: [
					],
					danweimingcheng: [
					],
					danweizhanghao: [
					],
					sfsh: [
					],
					shhf: [
					],
					clicktime: [
					],
					clicknum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
				},
				centerType: false,
			};
		},
		computed: {



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
						if(o=='gangweibianhao'){
							this.ruleForm.gangweibianhao = obj[o];
							this.ro.gangweibianhao = true;
							continue;
						}
						if(o=='gangweimingcheng'){
							this.ruleForm.gangweimingcheng = obj[o];
							this.ro.gangweimingcheng = true;
							continue;
						}
						if(o=='gangweileixing'){
							this.ruleForm.gangweileixing = obj[o];
							this.ro.gangweileixing = true;
							continue;
						}
						if(o=='zhuanye'){
							this.ruleForm.zhuanye = obj[o];
							this.ro.zhuanye = true;
							continue;
						}
						if(o=='tupian'){
							this.ruleForm.tupian = obj[o]?obj[o].split(",")[0]:'';
							this.ro.tupian = true;
							continue;
						}
						if(o=='zhuangtai'){
							this.ruleForm.zhuangtai = obj[o];
							this.ro.zhuangtai = true;
							continue;
						}
						if(o=='zhaopinrenshu'){
							this.ruleForm.zhaopinrenshu = obj[o];
							this.ro.zhaopinrenshu = true;
							continue;
						}
						if(o=='gangweiyaoqiu'){
							this.ruleForm.gangweiyaoqiu = obj[o];
							this.ro.gangweiyaoqiu = true;
							continue;
						}
						if(o=='shixin'){
							this.ruleForm.shixin = obj[o];
							this.ro.shixin = true;
							continue;
						}
						if(o=='jiezhiriqi'){
							this.ruleForm.jiezhiriqi = obj[o];
							this.ro.jiezhiriqi = true;
							continue;
						}
						if(o=='gangweijianjie'){
							this.ruleForm.gangweijianjie = obj[o];
							this.ro.gangweijianjie = true;
							continue;
						}
						if(o=='danweimingcheng'){
							this.ruleForm.danweimingcheng = obj[o];
							this.ro.danweimingcheng = true;
							continue;
						}
						if(o=='danweizhanghao'){
							this.ruleForm.danweizhanghao = obj[o];
							this.ro.danweizhanghao = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='clicknum'){
							this.ruleForm.clicknum = obj[o];
							this.ro.clicknum = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
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
						if((json.danweimingcheng!=''&&json.danweimingcheng) || json.danweimingcheng==0){
							this.ruleForm.danweimingcheng = json.danweimingcheng;
							this.ro.danweimingcheng = true;
						}
						if((json.danweizhanghao!=''&&json.danweizhanghao) || json.danweizhanghao==0){
							this.ruleForm.danweizhanghao = json.danweizhanghao;
							this.ro.danweizhanghao = true;
						}
					}
				});
				this.$http.get('option/gangweileixing/gangweileixing', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.gangweileixingOptions = res.data.data;
					}
				});
				this.$http.get('option/zhuanye/zhuanye', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.zhuanyeOptions = res.data.data;
					}
				});
				this.zhuangtaiOptions = "招聘中,已截止".split(',')

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`gangweixinxi/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(this.ruleForm.gangweibianhao){
					this.ruleForm.gangweibianhao = String(this.ruleForm.gangweibianhao)
				}
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


						await this.$http.post(`gangweixinxi/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
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
