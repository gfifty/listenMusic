<template>
	<view class="resshow">
		<p class="bigfont" placeholder="请输入搜索内容">{{searchRES}}</p>
		<span class="smallfont" v-if="searchRES">的搜索结果如下：</span>
		<p v-else>请输入搜索内容进行搜索。</p>
	</view>
</template>

<script setup>
	import { ref , computed } from 'vue';
	import {useSearchresStore} from '/store/data.js'
	
	// 使用pinia的state
	const searchRESStore = useSearchresStore()
	
	const searchRES =ref("")
	searchRES.value = searchRESStore.searchRes
	
	
</script>

<style>
.bigfont{
	display: inline-block;
	font-size: larger;
	font-family: 华文琥珀;
}
.smallfont{
	display: inline-block;
	font-size: smaller;
	margin-left: 20rpx;
	color: darkgrey;
}
.resshow{
	margin: 20rpx 0 0 20rpx;
}
</style>