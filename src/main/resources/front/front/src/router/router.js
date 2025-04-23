import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import zhuanyeList from '../pages/zhuanye/list'
import zhuanyeDetail from '../pages/zhuanye/detail'
import zhuanyeAdd from '../pages/zhuanye/add'
import xueshengList from '../pages/xuesheng/list'
import xueshengDetail from '../pages/xuesheng/detail'
import xueshengAdd from '../pages/xuesheng/add'
import yonggongdanweiList from '../pages/yonggongdanwei/list'
import yonggongdanweiDetail from '../pages/yonggongdanwei/detail'
import yonggongdanweiAdd from '../pages/yonggongdanwei/add'
import gangweileixingList from '../pages/gangweileixing/list'
import gangweileixingDetail from '../pages/gangweileixing/detail'
import gangweileixingAdd from '../pages/gangweileixing/add'
import gangweixinxiList from '../pages/gangweixinxi/list'
import gangweixinxiDetail from '../pages/gangweixinxi/detail'
import gangweixinxiAdd from '../pages/gangweixinxi/add'
import gangweishenqingList from '../pages/gangweishenqing/list'
import gangweishenqingDetail from '../pages/gangweishenqing/detail'
import gangweishenqingAdd from '../pages/gangweishenqing/add'
import pingjiagangweiList from '../pages/pingjiagangwei/list'
import pingjiagangweiDetail from '../pages/pingjiagangwei/detail'
import pingjiagangweiAdd from '../pages/pingjiagangwei/add'
import pingjiaxueshengList from '../pages/pingjiaxuesheng/list'
import pingjiaxueshengDetail from '../pages/pingjiaxuesheng/detail'
import pingjiaxueshengAdd from '../pages/pingjiaxuesheng/add'
import kaoqindakaList from '../pages/kaoqindaka/list'
import kaoqindakaDetail from '../pages/kaoqindaka/detail'
import kaoqindakaAdd from '../pages/kaoqindaka/add'
import gongzifafangList from '../pages/gongzifafang/list'
import gongzifafangDetail from '../pages/gongzifafang/detail'
import gongzifafangAdd from '../pages/gongzifafang/add'
import syslogList from '../pages/syslog/list'
import syslogDetail from '../pages/syslog/detail'
import syslogAdd from '../pages/syslog/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'zhuanye',
					component: zhuanyeList
				},
				{
					path: 'zhuanyeDetail',
					component: zhuanyeDetail
				},
				{
					path: 'zhuanyeAdd',
					component: zhuanyeAdd
				},
				{
					path: 'xuesheng',
					component: xueshengList
				},
				{
					path: 'xueshengDetail',
					component: xueshengDetail
				},
				{
					path: 'xueshengAdd',
					component: xueshengAdd
				},
				{
					path: 'yonggongdanwei',
					component: yonggongdanweiList
				},
				{
					path: 'yonggongdanweiDetail',
					component: yonggongdanweiDetail
				},
				{
					path: 'yonggongdanweiAdd',
					component: yonggongdanweiAdd
				},
				{
					path: 'gangweileixing',
					component: gangweileixingList
				},
				{
					path: 'gangweileixingDetail',
					component: gangweileixingDetail
				},
				{
					path: 'gangweileixingAdd',
					component: gangweileixingAdd
				},
				{
					path: 'gangweixinxi',
					component: gangweixinxiList
				},
				{
					path: 'gangweixinxiDetail',
					component: gangweixinxiDetail
				},
				{
					path: 'gangweixinxiAdd',
					component: gangweixinxiAdd
				},
				{
					path: 'gangweishenqing',
					component: gangweishenqingList
				},
				{
					path: 'gangweishenqingDetail',
					component: gangweishenqingDetail
				},
				{
					path: 'gangweishenqingAdd',
					component: gangweishenqingAdd
				},
				{
					path: 'pingjiagangwei',
					component: pingjiagangweiList
				},
				{
					path: 'pingjiagangweiDetail',
					component: pingjiagangweiDetail
				},
				{
					path: 'pingjiagangweiAdd',
					component: pingjiagangweiAdd
				},
				{
					path: 'pingjiaxuesheng',
					component: pingjiaxueshengList
				},
				{
					path: 'pingjiaxueshengDetail',
					component: pingjiaxueshengDetail
				},
				{
					path: 'pingjiaxueshengAdd',
					component: pingjiaxueshengAdd
				},
				{
					path: 'kaoqindaka',
					component: kaoqindakaList
				},
				{
					path: 'kaoqindakaDetail',
					component: kaoqindakaDetail
				},
				{
					path: 'kaoqindakaAdd',
					component: kaoqindakaAdd
				},
				{
					path: 'gongzifafang',
					component: gongzifafangList
				},
				{
					path: 'gongzifafangDetail',
					component: gongzifafangDetail
				},
				{
					path: 'gongzifafangAdd',
					component: gongzifafangAdd
				},
				{
					path: 'syslog',
					component: syslogList
				},
				{
					path: 'syslogDetail',
					component: syslogDetail
				},
				{
					path: 'syslogAdd',
					component: syslogAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
