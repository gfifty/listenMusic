<template>
	<view>
		<view class="titile">
		<H1>
			注册
		</H1>
		</view>
		<view class="inputArea">
			<input type="text" v-model="username"  placeholder="请输入用户名" />
			<input type="password" v-model="password" placeholder="请输入密码"/>
			<input type="password" v-model="repassword"  placeholder="请再次输入密码以确认"/>
		</view>
		<view class="login_button">
			<button type="button" @click="toRegister">
				注册
			</button>
		</view>
	</view>
</template>

<script setup>
import { ref } from 'vue';
import {useUserResStore} from '/store/logindata.js'
	const UserResStore = useUserResStore()
	const username=ref("")
	const password = ref("")
	const repassword = ref("")
	const toRegister = () =>{
		if (password.value != repassword.value) {
			uni.showToast({
				title:"两个输入的密码不同",
				icon:"none"
			})
		} else{
		uni.request({
			url:"http://localhost:8080/user/addOne",
			method:"POST",
			data:{
				username:username.value,
				password:password.value
			},
			success: (res) => {
				if (res.data.status == 200) {
					UserResStore.setUserRes(res.data)
					uni.navigateTo({
						url:'/pages/login/login'
					})
				} else{
					console.log("错误");
				}
			}
		})
		}
	}
</script>

<style>
page{
		 padding-bottom: 0rpx !important; 
		 min-height: auto !important;
	}
.titile{
	margin: 10% 0 10% 43%;
	font-size: 60rpx;
}
.login_button > button{
	width:30%;
	display: inline-block;
	justify-content: space-around;
	margin-left: 35%;
}
.inputArea{
	margin: 20rpx 0 10rpx 10%;
}
.inputArea > input{
	margin: 0rpx 100rpx 40rpx 0;
	border-bottom: 1px solid #aaaaaa;
}
</style>
