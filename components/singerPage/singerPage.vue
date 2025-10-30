<template>
      <view class="grid">
        <view class="item" v-for="(item, index) in searchResults" :key="index">
          <image :src="item.singerHead" mode="scaleToFill" class="circle"></image>
          <text class="item-text">{{ item.singerName }}</text>
        </view>
      </view>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import {useSearchresStore} from '/store/data.js'

const useSearchRESStore = useSearchresStore()
const ls = ref(useSearchRESStore.searchRes)
const searchResults = ref([]);

const goToLogin = () => {
  if (!userLoggedIn.value) {
    uni.navigateTo({ url: '/pages/login/login' });
  }
};
onMounted(()=>{
	console.log(ls);
	uni.request({
		url:"http://localhost:8080/singer/searchSinger",
		method:'POST',
		data:{
			singerName : ls.value
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
  width: 150rpx;
  height: 150rpx;
  border-radius: 50%;
  border: 1px solid #ddd;
}

.item-text {
  margin-top: 10rpx;
  font-size: 24rpx;
}
</style>