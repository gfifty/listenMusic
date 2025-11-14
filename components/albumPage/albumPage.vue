<!-- 该组件用于实现专辑页面的展示 -->
<template>
      <view class="grid">
        <view class="item" v-for="(p, index) in searchResults" :key="index" @click="openSinglist(p.albumId)">
          <image :src="p.albumHead" mode="aspectFit" class="circle"></image>
          <text class="item-text">{{ p.albumName }}</text>
		  <text class="item-num">共{{ p.albumNum }}首</text>
        </view>
      </view>
	  <miniPlayer></miniPlayer>
</template>

<script setup>
import miniPlayer from "@/components/miniPlayer/miniPlayer1.vue"
import { onMounted, ref } from 'vue';
import {useSearchresStore} from '/store/data.js'

const useSearchRESStore = useSearchresStore();
const ls = ref(useSearchRESStore.searchRes)
const searchResults = ref([]);

onMounted(()=>{
	console.log(ls);
	uni.request({
		url:"http://localhost:8080/album/searchAlbum",
		method:'POST',
		data:{
			albumName : ls.value
		},
		success: (res) => {
			if(res.data.status == 200){
				searchResults.value = res.data.data
				console.log(searchResults.value);
			}else{
				console.log("没找到");
			}
		},
		default:(res =>{
			console.log("失败了");
		})
		
	})
})
function openSinglist(albumId){
		  uni.navigateTo({
		   url: `/pages/albumDetail/albumDetail?albumId=${albumId}`
		  });
		console.log(1);
	}
</script>

<style>

.grid {
  display: flex;
  flex-wrap: wrap;
  margin-top: 20rpx;
}

.item {
  width: 33%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 50rpx;
}

.circle {
  width: 185rpx;
  height: 185rpx;
  border: 1px solid #ddd;

}

.item-text {
  margin-top: 10rpx;
  font-size: smaller;
  width: 185rpx;
  height: 35rpx;
  white-space: initial;
  overflow: hidden;
  text-overflow: ellipsis;
}
.item-num{
	margin-top: 10rpx;
	font-size: smaller;
	width: 185rpx;
	height: 35rpx;
	white-space: initial;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>