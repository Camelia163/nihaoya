import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import gangweileixing from '@/views/modules/gangweileixing/list'
	import aboutus from '@/views/modules/aboutus/list'
	import xuesheng from '@/views/modules/xuesheng/list'
	import pingjiagangwei from '@/views/modules/pingjiagangwei/list'
	import gangweishenqing from '@/views/modules/gangweishenqing/list'
	import syslog from '@/views/modules/syslog/list'
	import zhuanye from '@/views/modules/zhuanye/list'
	import pingjiaxuesheng from '@/views/modules/pingjiaxuesheng/list'
	import yonggongdanwei from '@/views/modules/yonggongdanwei/list'
	import gongzifafang from '@/views/modules/gongzifafang/list'
	import gangweixinxi from '@/views/modules/gangweixinxi/list'
	import messages from '@/views/modules/messages/list'
	import kaoqindaka from '@/views/modules/kaoqindaka/list'
	import config from '@/views/modules/config/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '公告信息',
		component: news
	}
	,{
		path: '/gangweileixing',
		name: '岗位类型',
		component: gangweileixing
	}
	,{
		path: '/aboutus',
		name: '关于我们',
		component: aboutus
	}
	,{
		path: '/xuesheng',
		name: '学生',
		component: xuesheng
	}
	,{
		path: '/pingjiagangwei',
		name: '评价岗位',
		component: pingjiagangwei
	}
	,{
		path: '/gangweishenqing',
		name: '岗位申请',
		component: gangweishenqing
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/zhuanye',
		name: '专业',
		component: zhuanye
	}
	,{
		path: '/pingjiaxuesheng',
		name: '评价学生',
		component: pingjiaxuesheng
	}
	,{
		path: '/yonggongdanwei',
		name: '用工单位',
		component: yonggongdanwei
	}
	,{
		path: '/gongzifafang',
		name: '工资发放',
		component: gongzifafang
	}
	,{
		path: '/gangweixinxi',
		name: '岗位信息',
		component: gangweixinxi
	}
	,{
		path: '/messages',
		name: '意见反馈',
		component: messages
	}
	,{
		path: '/kaoqindaka',
		name: '考勤打卡',
		component: kaoqindaka
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/newstype',
		name: '公告信息分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
