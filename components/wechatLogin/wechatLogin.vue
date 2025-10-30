<template>
	<view>
		<text  class="user_head_text" @click="login" v-if="!isLogined">
			登录注册
		</text>
	</view>
</template>

<script setup>
import { ref } from 'vue';
	const logincode=ref('')
	const isLogined =ref(userResStore.isLogined)
	console.log(isLogined.value);
	
	const login= () =>{
		uni.login({
			provider:"weixin",
			success:function(loginRes){
				logincode=loginRes.code
			}
		})
	}
</script>

<style>

</style>