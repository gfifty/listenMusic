<template>
	<header class="header">
	  <div class="logo">
	    <i class="fas fa-music icon-logo"></i>
	    <span>My Music</span>
	  </div>
	  <div class="search-bar">
	    <i class="fas fa-search icon-search" @click="toSearch"></i>
	    <input type="text" placeholder="搜索音乐..." v-model="searchRES" />
	  </div>
	</header>
		<!-- <view>
			<text  class="user_head_text" @click="login" v-if="!isLogined">
				登录注册
			</text>
			<image v-else src="/static/组件/head.jpg" mode="aspectFill" class="user_head"></image>
			<button class="search" @click="toSearch">搜索</button>
			<input class="searchBAR" type="text" v-model="searchRES" placeholder="请输入搜索内容"/>
		</view> -->
</template>

<script setup>
	import searchresult from "/components/searchResult/searchResult.vue"
	import {useSearchresStore} from "/store/data.js"
	import {useUserResStore} from "/store/logindata.js"
	import { onShow } from "@dcloudio/uni-app"
	import { ref } from 'vue'

	//使用Pinia的state和actions
	const code=ref('')
	const searchRESStore = useSearchresStore();
	const searchRES = ref('')
	const userResStore =useUserResStore();
	const userRES = ref("")
	const isLogined =ref(userResStore.isLogined)
	console.log(isLogined.value);
	
	onShow(() => {
	  console.log('页面显示了');
	  // 每次页面从后台回到前台时，检查登录状态
	  isLogined.value = userResStore.isLogined;
	  console.log('用户登录状态：', isLogined.value);
	});
	
	const toSearch = () =>{
		if (searchRES.value.trim()) {
			// 更新Pinia，store中的searchRES
			searchRESStore.setSearchRES(searchRES.value)
			//跳转到对应页面
				uni.navigateTo({
					url:`/pages/search/search`
				})
				console.log(searchRES);
		} else{
			uni.showToast({
				title:'请输入搜索内容',
				icon:'none'
			})
		}
	}
	// 普通登录注册
	const login= () =>{
		uni.login({
			provider:"weixin",
			success:function(loginRes){
				code:loginRes.code
				console.log("用户id在这",loginRes.code);
				
			}
		}),
		uni.getUserInfo({
			provider:"weixin",
			success:function(loginRes){
				// 获取用户信息
				const wxUserInfo = loginRes.userInfo
				console.log(wxUserInfo.avatarUrl);
				console.log(wxUserInfo.nickName);
				console.log(wxUserInfo);
			}
		})
		uni.navigateTo({
			url:"/pages/login/login"
		})
	}
	// 微信登录
	// function login(){
	// 	 wx.showModal({                     //手动授权弹窗
	// 	      title: '授权登录提示',
	// 	      content: '是否允许授权？',
	// 	      showCancel: true,
	// 	      confirmText: '允许',
	// 	      confirmColor: '#669F76',
	// 	      cancelColor: '#CC463D',
	// 	      success: async res => {
	// 	        if (res.confirm) {     //用户确认
	// 	          const { userInfo: { nickName, avatarUrl } } = await
	// 	            wx.getUserProfile({    //获取用户信息，最新版本已不支持弹窗
	// 	              desc: '用户授权',
	// 	            })
		 
	// 	          let { result } = await wx.cloud.callFunction({  // 调用云函数
	// 	            name: 'loginOrSignup',
	// 	            data: {
	// 	              nickName,
	// 	              avatarUrl
	// 	            }
	// 	          })
	// 	          this.setData({ userInfo: result })
	// 	          getApp().globalData.userStatus = true   //用于检查用户是否登录的全局变量
	// 	        } else if (res.cancel) {     //用户取消
	// 	          wx.showToast({
	// 	            title: '取消授权',
	// 	            mask: true,
	// 	            icon: 'error'
	// 	          })
	// 	        }
	// 	      }
	// 	    })
	// }
</script>

<style>
/* 顶部栏 */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
  background: linear-gradient(to right, #9cdf7e, #ffcce6);
  color: #fff;
  border-bottom: 1px solid #ddd;
}
.logo {
  display: flex;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
}
.icon-logo {
  margin-right: 8px;
  color: #fff;
  font-size: 28px;
}
.search-bar {
  display: flex;
  align-items: center;
  background: #fff;
  border-radius: 20px;
  padding: 5px 10px;
  width: 180px;
}
.icon-search {
  margin-right: 10px;
  color: #9cdf7e;
}
.search-bar input {
  border: none;
  outline: none;
  font-size: 14px;
  width: 100%;
}
</style>