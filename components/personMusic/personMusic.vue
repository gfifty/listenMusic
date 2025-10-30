<template>
	<view>
		<uni-list-item v-for="(p,index) in singleList" :key="index" @tap="openSinglist(p.singlistId)">
			<view class="firstMusic">
				<image :src="p.singlistHead" mode="aspectFit" class="cover"></image>
				<p class="namesingle">{{p.singlistName}}</p>
			</view>
		</uni-list-item>
		<button  @click="open">创建新的歌单</button>
		<uni-popup ref="popup" type="bottom" border-radius="10px 10px 0 0">
					<view class="popup-container">
						<input 
							type="text" 
							placeholder="请输入歌单名称" 
							v-model="newSinglistName"
							class="input"
						/>
						<textarea 
							placeholder="请输入歌单简介（选填）" 
							v-model="newSinglistDesc" 
							class="textarea"
						></textarea>
						<button @click="chooseImage">选择封面图片</button>					
						<button @click="createSinglist">创建</button>
					</view>
				</uni-popup>
	</view>
</template>

<script setup>
import { ref } from 'vue';
import {useUserResStore} from "/store/logindata.js"
const userResStore = useUserResStore()
const singleList=ref([])
// 存储已选择的图片路径
const selectedImage = ref('');
// 存储上传后的图片链接
const uploadedUrl = ref('');
const SMMS_API_KEY = 'TkySYmMinE36XLUlKJmLI1PceSFaFtac';
// 模态框控制
const popup = ref(null);
const open = () => {
	if (popup.value) popup.value.open('bottom');
};
// 歌单相关数据
const newSinglistName = ref('');
const newSinglistDesc = ref('');
const previewImage = ref(null);
// 获取歌单数据
uni.request({
	url:"http://localhost:8080/singlist/lookSinglist",
	method:'POST',
	data:{
		userId:userResStore.userRes.userId
	},
	success(res) {
		if(res.data.status ==200){
			singleList.value=res.data.data
		}
	},fail: (err) => {
		console.log(err);
	}
})
// 打开对应的歌单
function openSinglist(singlistId){
		  uni.navigateTo({
		   url: `/pages/singlistDetail/singlistDetail?singlistId=${singlistId}`
		  });
		console.log(singlistId);
}
	// 创建歌单
const createSinglist = async () => {
	  if (!newSinglistName.value.trim()) {
	    uni.showToast({ title: '歌单名称不能为空', icon: 'none' });
	    return;
	  }
	  try {
	    let singlistHead = '';
	    if (selectedImage.value) {
	      // 上传封面图片
	      const uploadResult = await uploadToSmms(selectedImage.value);
	      if (uploadResult && uploadResult.success) {
	        singlistHead = uploadResult.data.url; // 获取上传后的图片链接
			console.log("11");
	      } else {
	        uni.showToast({ title: '图片上传失败', icon: 'none' });
	        return;
	      }
}
	
	    // 提交歌单信息到后端
	    uni.request({
	      url: "http://localhost:8080/singlist/createSinglist",
	      method: "POST",
	      data: {
	        userId: userResStore.userRes.userId,
	        singlistName: newSinglistName.value,
	        singlistIntroduction: newSinglistDesc.value || '',
	        singlistHead,
	      },
	      success(res) {
	        if (res.data.status === 200) {

	          uni.showToast({ title: '创建成功', icon: 'success' });
	
	          // 刷新歌单列表
	          singleList.value.push({
	            singlistName: newSinglistName.value,
	            singlistHead,
	            singlistIntroduction: newSinglistDesc.value || '',
	            singlistId: res.data.data.singlistId, // 从后端返回结果获取歌单ID
	          });
			  console.log(singleList.value);
	
	          // 关闭弹窗并清空输入内容
	          popup.value.close();
	          newSinglistName.value = '';
	          newSinglistDesc.value = '';
	          previewImage.value = null;
	          selectedImage.value = null;
	        } else {
	          uni.showToast({ title: '创建失败', icon: 'none' });
	        }
	      },
	      fail(err) {
	        uni.showToast({ title: '请求失败', icon: 'none' });
	      }
	    });
	  } catch (err) {
	    uni.showToast({ title: '图片上传失败', icon: 'none' });
	  }
	};
	
	//选择图片
	const chooseImage = () => {
	  uni.chooseImage({
	    count: 1, // 限制只能选择 1 张图片
	    success: (chooseRes) => {
	      selectedImage.value = chooseRes.tempFilePaths[0]; // 保存选择的图片路径
		  previewImage.value = selectedImage;
	      uni.showToast({
	        title: '图片选择成功',
	        icon: 'success',
	      });
	    },
	    fail: () => {
	      uni.showToast({
	        title: '图片选择失败',
	        icon: 'error',
	      });
	    },
	  });
	};
	
	// 上传图片到 sm.ms
	const uploadImage = async () => {
	  if (!selectedImage.value) {
	    uni.showToast({
	      title: '请先选择图片',
	      icon: 'none',
	    });
	    return;
	  }
	  try {
	    const result = await uploadToSmms(selectedImage.value);
	    if (result && result.success) {
	      uploadedUrl.value = result.data.url;
	      uni.showToast({
	        title: '上传成功',
	        icon: 'success',
	      });
	      console.log('上传成功，图片链接:', uploadedUrl.value);
	    } else {
	      uni.showToast({
	        title: result.message || '上传失败',
	        icon: 'error',
	      });
	    }
	  } catch (error) {
	    console.error('上传失败:', error);
	    uni.showToast({
	      title: '上传失败',
	      icon: 'error',
	    });
	  }
	};
	
	// 调用 sm.ms 的上传接口获取返回结果
	const uploadToSmms = (filePath) => {
	  return new Promise((resolve, reject) => {
	    uni.uploadFile({
	      url: 'https://sm.ms/api/v2/upload',
	      filePath,
	      name: 'smfile', // sm.ms 接收的字段名是 `smfile`
	      header: {
	        Authorization: SMMS_API_KEY, // 在请求头中添加 API Key
	      },
	      success: (uploadRes) => {
	        const res = JSON.parse(uploadRes.data); // sm.ms 返回的 JSON 数据
	        resolve(res);
	      },
	      fail: (err) => {
	        reject(err);
	      },
	    });
	  });
	};

</script>

<style>
.firstMusic {
	  display: flex;
	  align-items: center;
	  padding: 10rpx 0rpx;
	  border-bottom: 1px solid #f2f3f4;
	}
	
	.firstMusic>p{
		font-size: medium;
		height: 100rpx;
		line-height: 100rpx;
		display: inline-block;
	}
	.firstMusic>image{
		font-size: medium;
		height: 100rpx;
		line-height: 100rpx;
		display: inline-block;
	}
	.cover{
		width: 80rpx;
		height: 80rpx;
	}
	.numsingle {
		width: 40rpx;
		text-align: center;
		color: #AAAAAA;
		display: inline-block;
		margin: 0 20rpx 0 5rpx;
	}
	.namesingle{
		display: inline-block;
		width: 240rpx;
		margin:0 20rpx 0 20rpx;
		color: #555555;
		font-weight: bolder;
		overflow-x: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	.artistsingle{
		display: inline-block;
		width: 200rpx;
		color: #555555;
		font-weight: bolder;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	.love{
		color:red;
		margin: 0 20rpx 0 0;
	}
	.playButtonsingle{
		margin-left: 20rpx;
	}
	.popup-container{
		background-color: #555555;
	}

	.previewimg{
		width: 10rpx;
		height: 10rpx;
	}
</style>