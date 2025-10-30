<template>
	<view >
		<view  style="background-color: #999999; height: 150rpx;line-height: 150rpx;">
		<text class="friend">好友列表</text>
	</view>
		<uni-list>
			<uni-list-item v-for="(p,index) in userlist" :key="index" class="one">
				<image :src="p.userHead" mode="" class="header-image"></image>
				<text class="friendtext">{{p.username}}</text>
			</uni-list-item>
		</uni-list>
	</view>
</template>

<script setup>
import { ref } from "vue"
import {useUserResStore} from  "../../store/logindata.js"
import { onTabItemTap } from "@dcloudio/uni-app"
const userResStore =useUserResStore()
const userlist =ref([])

	const user = ref({})
	
	 uni.request({
	 	url:'http://localhost:8080/attention/getAttention',
		method:"POST",
		data:{
			userId:userResStore.userRes.userId
		},
		success: (res) => {
			if (res.data.status==200) {
					userlist.value=res.data.data			
			} else{
				console.log("没找到");
			}
		},
		fail: (err) => {
			console.log(err);
		}
		
	 })
	 	// 点击我的Tabbar的判断
	 	onTabItemTap((e)=>{
	 		if (!userResStore.isLogined) {
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
.friend{
	font-size: 50rpx;	
	text-align: center;
	margin: 100rpx 0 30rpx 260rpx;
	}
	.one{
		height: 120rpx;
	}
.header-image{
	display: inline-block;
	width: 100rpx;
	height: 100rpx;
}
.one{
	display: block;
}
.friendtext{
	margin-left: 50rpx;
}
</style>
