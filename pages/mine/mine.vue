<template>
	<view class="page">
		<view class="profile-section">
		  <view class="profile-header">
		    <image class="avatar" :src="UserResStore.userRes.userHead" mode="aspectFill"></image>
		    <view class="user-info">
		      <text class="username">{{ UserResStore.userRes.username }}</text>
		    </view>
		  </view>
	</view>
	<view class="">
		<person-music v-if="toSelect === 'personmusic'"></person-music>
	</view>
	<!-- 退出登录 -->
			<view class="logout-section">
				<button class="logout-btn" @click="unLogin()">
					退出登录
				</button>
			</view>
	</view>
	<miniPlayer></miniPlayer>
</template>

<script setup>
	import miniPlayer from "@/components/miniPlayer/miniPlayer1.vue"
	import { ref } from "vue";
	import pageHead from "/components/pageHead/pageHead.vue"
	import personMusic from "/components/personMusic/personMusic.vue"
	import {useUserResStore} from '/store/logindata.js'
	import { onTabItemTap } from "@dcloudio/uni-app"
	
	const UserResStore = useUserResStore()
	const selectedButton = ref(1)
	const toSelect=ref("personmusic")
	
	function unLogin(){
		UserResStore.resetUser();
		// 避免闪屏问题

			uni.switchTab({
				url:'/pages/index/index'
			})
	}
	// 点击我的Tabbar的判断
	onTabItemTap((e)=>{
		if (!UserResStore.isLogined) {
			uni.redirectTo({
				url: '/pages/login/login'
			});
			
			uni.showToast({
				title:"请先进行登录",
				icon:"none"
			})
		}
	})

</script>

<style>
.page{
		background: linear-gradient(135deg, #87CEFA 0%, #00BFFF 50%, #40E0D0 100%);
}
.profile-section {
  background: linear-gradient(135deg, #87CEFA 0%, #00BFFF 50%, #40E0D0 100%);
  color: #fff;
  padding: 40rpx 30rpx;
  border-radius: 0 0 40rpx 40rpx;
  box-shadow: 0 10rpx 20rpx rgba(0, 0, 0, 0.1);
}

.profile-header {
  display: flex;
  align-items: center;
  margin-bottom: 20rpx;
}

.avatar {
  width: 140rpx;
  height: 140rpx;
  border-radius: 50%;
  border: 4rpx solid rgba(255, 255, 255, 0.3);
  margin-right: 20rpx;
}

.user-info .username {
  font-size: 34rpx;
  font-weight: 600;
  margin-bottom: 6rpx;
}

/* 退出登录 */
.logout-section {
	padding: 30rpx;
}

.logout-btn {
	background: #fff;
	border: 2rpx solid #eee;
	border-radius: 15rpx;
	color: #ff4757;
	font-size: 30rpx;
	padding: 20rpx 0;
}


</style>
