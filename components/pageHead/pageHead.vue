<template>
	<header class="header">
	  <view class="logo">
	    <span>My Music</span>
	  </view>
	  <view class="search-bar">
	    <i @click="toSearch"></i>
	    <input type="text" class="search-txt" placeholder="搜索音乐..." v-model="searchRES" />
		<uni-icons type="search" size="20" @click="toSearch"></uni-icons>
		<!-- <text class="search-btn" @click="toSearch">搜索</text> -->
	  </view>
	</header>
</template>

<script setup>
	import {svgIcon} from "/components/svgIcon/svgIcon.vue"
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
  color: #9cdf7e;
  outline: none;
  font-size: 14px;
  width: 100%;
}
</style>
