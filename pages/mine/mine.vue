<template>
	<button @click="unLogin">退出登录</button>
	<view class="personCard">
		<image :src="UserResStore.userRes.userHead" mode="" class="user_header_image"></image>
		<text class="user_name">{{UserResStore.userRes.username}}</text>
	</view>
	<view >
		<uni-list >
			<uni-list-item><p class="selectres" :class="{ 'active': selectedButton === 1 }" @click="selectpage('personmusic');changeColor(1)">我的收藏</p></uni-list-item>
		</uni-list>
	</view>
	<view class="">
		<person-music v-if="toSelect === 'personmusic'"></person-music>
	</view>
</template>

<script setup>
	import { ref } from "vue";
	import pageHead from "/components/pageHead/pageHead.vue"
	import personMusic from "/components/personMusic/personMusic.vue"
	import {useUserResStore} from '/store/logindata.js'
	import { onTabItemTap } from "@dcloudio/uni-app"
	
	const UserResStore = useUserResStore()
	const selectedButton = ref(1)
	const toSelect=ref("personmusic")
	
	function unLogin(){
		UserResStore.setIsLogined(false)
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
.personCard{
	height: 216rpx;
	background-color: #f2f2f2;
	margin-top: -50rpx;
	}
	.changehuman{
		width: 250rpx;
		margin-left: 510rpx;
		margin-top: -100rpx;
		font-size: 30rpx;
	}
.user_header_image{
	display: inline-block;
	position: absolute;
	width: 150rpx;
	height: 150rpx;
	border-radius: 75rpx;
	border: 1rpx solid black;
	margin: 26rpx 68rpx 0 64rpx;
}
.user_name{
	display: block;
	margin: 36rpx 0 20rpx 286rpx;
	padding-top: 26rpx;
	font-size: 50rpx;
}
.user_fan{
	margin: 0rpx 0 20rpx 286rpx;
	font-size: 25rpx;
}
.user_attention{
	margin-left: 30rpx;
	font-size: 25rpx;
}
.selectres{
		width: 100%;
		display: inline-block;
		background-color: #F2F2F2;
		height: 90rpx;
		border: 0px solid black;
		text-align: center;
		line-height: 90rpx;
		border-radius: 0;
	}
	.active{
		background-color: #aaaaaa;
	}
</style>
