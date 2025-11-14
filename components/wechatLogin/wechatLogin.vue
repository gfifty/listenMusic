<template>
	<view>
		<text  class="user_head_text" @click="login" v-if="!isLogined">
			登录/注册
		</text>
	</view>
</template>

<script setup>
import { ref } from 'vue';
	const logincode=ref('')
	const isLogined =ref(userResStore.isLogined)
	console.log(isLogined.value);
	
	uni.login({
	  provider: 'weixin',
	  onlyAuthorize: true // 仅请求授权，不强制绑定手机号
	}).then(loginRes => {
	  if (loginRes.errMsg === 'login:ok') {
	    const code = loginRes.code; // 关键！用户临时登录凭证
	    // 2. 将 code 发送给后端
	    this.sendCodeToBackend(code);
	  } else {
	    console.error('微信登录失败:', loginRes.errMsg);
	  }
	});
</script>

<style>

</style>