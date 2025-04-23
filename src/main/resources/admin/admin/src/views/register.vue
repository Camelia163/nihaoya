<template>
	<div>
		<div id="particles"></div>
		<div class="register-container">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">勤工助学管理系统</div>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('xueshengzhanghao')?'required':''">学生账号：</div>
						<el-input  v-model="ruleForm.xueshengzhanghao"  autocomplete="off" placeholder="学生账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('xueshengxingming')?'required':''">学生姓名：</div>
						<el-input  v-model="ruleForm.xueshengxingming"  autocomplete="off" placeholder="学生姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="xueshengtouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in xueshengxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('dianhuahaoma')?'required':''">电话号码：</div>
						<el-input  v-model="ruleForm.dianhuahaoma"  autocomplete="off" placeholder="电话号码"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('zhuanye')?'required':''">专业：</div>
						<el-select v-model="ruleForm.zhuanye" placeholder="请选择专业" >
							<el-option
								v-for="(item,index) in xueshengzhuanyeOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonggongdanwei'">
						<div class="lable" :class="changeRules('danweizhanghao')?'required':''">单位账号：</div>
						<el-input  v-model="ruleForm.danweizhanghao"  autocomplete="off" placeholder="单位账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonggongdanwei'">
						<div class="lable" :class="changeRules('danweimingcheng')?'required':''">单位名称：</div>
						<el-input  v-model="ruleForm.danweimingcheng"  autocomplete="off" placeholder="单位名称"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonggongdanwei'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonggongdanwei'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonggongdanwei'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="yonggongdanweitouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonggongdanwei'">
						<div class="lable" :class="changeRules('dianhuahaoma')?'required':''">电话号码：</div>
						<el-input  v-model="ruleForm.dianhuahaoma"  autocomplete="off" placeholder="电话号码"  type="text"  />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
	require('@/utils/particles.js');
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
            xueshengxingbieOptions: [],
            xueshengzhuanyeOptions: [],
			particlesJson: {"retina_detect":true,"particles":{"number":{"density":{"value_area":800,"enable":true},"value":150},"move":{"straight":false,"random":false,"bounce":false,"enable":true,"attract":{"rotateX":3000,"enable":false,"rotateY":3000},"speed":2,"direction":"none","out_mode":"out"},"color":{"value":"#fff"},"shape":{"image":{"width":100,"src":"img/github.svg","height":100},"polygon":{"nb_sides":5},"type":"circle","stroke":{"color":"#000000","width":0}},"size":{"anim":{"size_min":0,"sync":false,"enable":false,"speed":20},"random":false,"value":2},"line_linked":{"width":1,"distance":100,"color":"#ffffff","opacity":1,"enable":true},"opacity":{"anim":{"opacity_min":0,"sync":false,"enable":false,"speed":1},"random":false,"value":0.5}},"interactivity":{"detect_on":"canvas","events":{"resize":true,"onclick":{"mode":"push","enable":true},"onhover":{"mode":"grab","enable":true}},"modes":{"grab":{"distance":100,"line_linked":{"opacity":1}},"bubble":{"duration":0.7,"distance":200,"size":80,"opacity":0,"speed":3},"repulse":{"duration":0.4,"distance":200},"push":{"particles_nb":4},"remove":{"particles_nb":2}}}}
		};
	},
	mounted(){
		particlesJS('particles',this.particlesJson);
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='xuesheng'){
				this.ruleForm = {
					xueshengzhanghao: '',
					xueshengxingming: '',
					mima: '',
					touxiang: '',
					xingbie: '',
					dianhuahaoma: '',
					zhuanye: '',
					sfsh: '',
					shhf: '',
				}
			}
			if(this.tableName=='yonggongdanwei'){
				this.ruleForm = {
					danweizhanghao: '',
					danweimingcheng: '',
					mima: '',
					touxiang: '',
					dianhuahaoma: '',
					sfsh: '',
					shhf: '',
				}
			}
			if ('xuesheng' == this.tableName) {
				this.rules.xueshengzhanghao = [{ required: true, message: '请输入学生账号', trigger: 'blur' }]
			}
			if ('xuesheng' == this.tableName) {
				this.rules.xueshengxingming = [{ required: true, message: '请输入学生姓名', trigger: 'blur' }]
			}
			if ('xuesheng' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('yonggongdanwei' == this.tableName) {
				this.rules.danweizhanghao = [{ required: true, message: '请输入单位账号', trigger: 'blur' }]
			}
			if ('yonggongdanwei' == this.tableName) {
				this.rules.danweimingcheng = [{ required: true, message: '请输入单位名称', trigger: 'blur' }]
			}
			if ('yonggongdanwei' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			this.xueshengxingbieOptions = "男,女".split(',')
			this.$http({
				url: `option/zhuanye/zhuanye`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.xueshengzhuanyeOptions = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	},
	created() {
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        xueshengtouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        yonggongdanweitouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			  if(this.tableName=='xuesheng'){
				  this.ruleForm.sfsh = '待审核'
			  }
			if((!this.ruleForm.xueshengzhanghao) && `xuesheng` == this.tableName){
				this.$message.error(`学生账号不能为空`);
				return
			}
			if((!this.ruleForm.xueshengxingming) && `xuesheng` == this.tableName){
				this.$message.error(`学生姓名不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `xuesheng` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `xuesheng` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`xuesheng` == this.tableName && this.ruleForm.dianhuahaoma &&(!this.$validate.isMobile(this.ruleForm.dianhuahaoma))){
				this.$message.error(`电话号码应输入手机格式`);
				return
			}
			  if(this.tableName=='yonggongdanwei'){
				  this.ruleForm.sfsh = '待审核'
			  }
			if((!this.ruleForm.danweizhanghao) && `yonggongdanwei` == this.tableName){
				this.$message.error(`单位账号不能为空`);
				return
			}
			if((!this.ruleForm.danweimingcheng) && `yonggongdanwei` == this.tableName){
				this.$message.error(`单位名称不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `yonggongdanwei` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `yonggongdanwei` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`yonggongdanwei` == this.tableName && this.ruleForm.dianhuahaoma &&(!this.$validate.isMobile(this.ruleForm.dianhuahaoma))){
				this.$message.error(`电话号码应输入手机格式`);
				return
			}
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20240926/67d8b8cb5e9e4bce9d9e8a301757a3ed.jpg) no-repeat center center / 100% 100%;
	background: url(http://codegen.caihongy.cn/20240926/67d8b8cb5e9e4bce9d9e8a301757a3ed.jpg) no-repeat center center / 100% 100%;
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	position: relative !important;
	.rgs-form {
		.rgs-form2 {
		margin: 0px 0;
		width: 100%;
		position: relative;
		}
		border-radius: 10px;
		padding: 20px 50px 60px 120px;
		margin: 20px auto 60px;
		z-index: 1002;
		background: rgba(77,151,195,.9);
		width: 750px;
		height: auto;
		.title {
			padding: 0;
			margin: 10px 0 20px 0;
			color: #2d4448;
			background: none;
			font-weight: 600;
			width: 100%;
			font-size: 22px;
			line-height: 24px;
			text-align: center;
		}
		.list-item {
			border-radius: 8px;
			padding: 0 10px;
			margin: 0 0 20px;
			background: #fff;
			display: flex;
			width: calc(100% - 0px);
			border-color: #d5d0cd;
			border-width: 4px 0 0;
			align-items: center;
			position: relative;
			border-style: solid;
			flex-wrap: wrap;
			/deep/ .el-form-item__content {
				display: block;
			}
			.lable {
				padding: 0 5px 0 0;
				color: #000;
				left: -130px;
				width: 130px;
				font-size: 16px;
				line-height: 40px;
				position: absolute !important;
				text-align: right;
			}
			.el-input {
				width: 100%;
			}
			.el-input /deep/ .el-input__inner {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 40px;
			}
			.el-input /deep/ .el-input__inner:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 40px;
			}
			.el-input-number {
				width: 100%;
			}
			.el-input-number /deep/ .el-input__inner {
				text-align: center;
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 40px;
			}
			.el-input-number /deep/ .el-input__inner:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
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
				width: 100%;
			}
			.el-select /deep/ .el-input__inner {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-select /deep/ .el-input__inner:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor /deep/ .el-input__inner {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-date-editor /deep/ .el-input__inner:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px 0 30px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-date-editor.el-input {
				width: 100%;
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
				border: 2px solid #ddd;
				cursor: pointer;
				border-radius: 10px;
				margin: 10px 0 0;
				color: #999;
				background: none;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload-list .el-upload-list__item {
				border: 2px solid #ddd;
				cursor: pointer;
				border-radius: 10px;
				margin: 10px 0 0;
				color: #999;
				background: none;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload .el-icon-plus {
				border: 2px solid #ddd;
				cursor: pointer;
				border-radius: 10px;
				margin: 10px 0 0;
				color: #999;
				background: none;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload__tip {
				color: #666;
				font-size: 15px;
			}
			/deep/ .el-input__inner::placeholder {
				color: #999;
				font-size: 16px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: red;
				left: 125px;
				line-height: 42px;
				position: inherit;
				content: "*";
				order: -1;
			}
			.editor {
				background: none;
				width: 100%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border: 0px solid #e6e6e6;
				border-radius: 0px 0 0 0px;
				padding: 0 10px;
				margin: 0;
				color: #666;
				background: none;
				flex: 1;
				width: 100%;
				font-size: 14px;
				height: 38px;
			}
			input:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px 0 0 0px !important;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
				font-size: 14px;
				height: 38px;
			}
			input::placeholder {
				color: #999;
				font-size: 16px;
			}
			button {
				border: 0px solid #e6e6e6;
				cursor: pointer;
				border-radius: 0 10px 10px 0;
				padding: 0;
				margin: 0 0 0 10px;
				color: #333;
				background: #fff;
				width: 120px;
				font-size: 15px;
				height: 38px;
			}
			button:hover {
				opacity: 0.8;
			}
		}
		.register-btn {
			display: flex;
			width: 100%;
			flex-wrap: wrap;
		}
		.register-btn1 {
			width: 100%;
			text-align: center;
			order: 3;
		}
		.register-btn2 {
			width: 100%;
		}
		.r-btn {
			border: 10px solid #f9f9f9;
			cursor: pointer;
			padding: 0 10px;
			margin: 0 auto 10px;
			color: #fff;
			bottom: -124px;
			font-weight: 600;
			font-size: 22px;
			border-radius: 100%;
			box-shadow: inset 12px 0px 16px 0px #3d9cb2;
			left: 32%;
			background: #4dc2de;
			width: auto;
			position: absolute;
			min-width: 110px;
			height: 110px;
		}
		.r-btn:hover {
			opacity: 1;
		}
		.r-login {
			cursor: pointer;
			padding: 0;
			color: #333;
			display: inline-block;
			width: 100%;
			font-size: 15px;
			line-height: 40px;
			text-align: right;
		}
		.r-login:hover {
			opacity: 1;
		}
	}
}
	
	#particles{
		background-repeat: no-repeat;
		z-index: 1;
		background-size: cover;
		width: 100%;
		position: absolute;
		background-position: 50% 50%;
		height: 100%;
	}
	::-webkit-scrollbar {
	  display: none;
	}
</style>
