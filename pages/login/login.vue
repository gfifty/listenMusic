<template>
	<view>
		<view class="titile">
		<H1>
			登录
		</H1>
		</view>
		<view class="inputArea">
			<input type="text" v-model="username"  placeholder="请输入用户名" />
			<input type="password" v-model="password" placeholder="请输入密码"/>
		</view>
		<view class="login_button">
			<button type="button" @click="tologin">
				登录
			</button>
			<button type="button" @click="toRegister">
				注册
			</button>
		</view>
	</view>
</template>

<script setup>
import { ref } from "vue"
import {useUserResStore} from '/store/logindata.js'
	const UserResStore = useUserResStore()
	const UserRes = ref({})
	const username =ref("")
	const password =ref("")
	
	const tologin = () =>{
		if (!username.value || !password.value) {
		uni.showToast({
			"title":"用户名或密码不能为空",
			icon:'none'
		})
		} else{
			// 向后端发请求
			uni.request({
				url:`http://localhost:8080/user/login`,
				method:"POST",
				header: {
				        'Content-Type': 'application/json' // 发送 JSON 数据
				    },
				    data: JSON.stringify({
				        username: username.value,
				        password: password.value
				    }),
				success: (res) => {
					if (res.data.status == 200 ) {
						UserResStore.setUserRes(res.data.data)
						UserResStore.setIsLogined(true)
						uni.switchTab({
							url:'/pages/index/index'
						})
					} else{
						uni.showToast({
							title:"用户名或密码错误",
							icon:'error'
						})
					}
				},
				fail: (err) =>{
					console.log(err);
				}
			})
		}
	}
	const toRegister = () =>{
		uni.navigateTo({
			url:"/pages/register/register"
		})
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
	margin-left: 13%;
}
.inputArea{
	margin: 20rpx 0 10rpx 10%;
}
.inputArea > input{
	margin: 0rpx 100rpx 40rpx 0;
	border-bottom: 1px solid #aaaaaa;
}
</style>
