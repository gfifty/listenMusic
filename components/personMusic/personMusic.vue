<template>
	<view>
		<view class="playlist-section">
		  <view class="section-title">
		    <text>我的歌单</text>
		    <text class="sub-title">共{{ singleList.length }}个</text>
		  </view>
		  <view class="playlist-grid">
		    <view
		      class="playlist-item"
		      v-for="(singleList, index) in singleList"
		      :key="index"
		      @click="openSinglist(singleList.singlistId)"
		    >
		      <view class="playlist-cover-container">
		        <image :src="singleList.singlistHead" class="playlist-cover" mode="aspectFill"></image>
		      </view>
		      <view class="playlist-info">
		        <text class="playlist-name">{{ singleList.singlistName }}</text>
		      </view>
		    </view>
		  </view>
		</view>
		
		<view class="create-music-btn" @click="open">
		<image src="/static/iconfont/y_icon_line_edit_add.svg" style="width:50rpx;height:50rpx;" mode="widthFix"></image>
		</view>
		
		<uni-popup ref="popup" type="bottom" border-radius="10px 10px 0 0">
				<view class="modal-content">
					<view class="modal-header">
						<text class="modal-title">创建新歌单</text>
						<text class="fa fa-times modal-close" @click="closeModal"></text>
					</view>
					
					<view class="form-group">
						<text class="form-label">歌单名称</text>
					<input class="form-input" type="text" placeholder="请输入歌单名称" v-model="newSinglistName"/>
					</view>
					
					<view class="form-group">
						<text class="form-label">歌单简介</text>
					<textarea class="form-textarea" placeholder="请输入歌单简介（选填）" v-model="newSinglistDesc"></textarea> 
					</view>
					
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
			console.log(singleList.value);
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


/* 歌单区 */
.playlist-section {
  padding: 30rpx;
}

.section-title {
  font-size: 34rpx;
  font-weight: 600;
  margin-bottom: 20rpx;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.sub-title {
  color: #888;
  font-size: 26rpx;
  font-weight: normal;
}

.playlist-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 24rpx;
}

.playlist-item {
  background: #fff;
  border-radius: 20rpx;
  overflow: hidden;
  box-shadow: 0 10rpx 20rpx rgba(0, 0, 0, 0.05);
  transition: transform 0.3s;
}

.playlist-item:active {
  transform: scale(0.96);
}

.playlist-cover-container {
  position: relative;
  width: 100%;
  padding-bottom: 100%;
  overflow: hidden;
}

.playlist-cover {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.playlist-info {
  background-color: linear-gradient(135deg, #87CEFA  0%, #00BFFF  50%, #40E0D0 100%);
  border-top: rgba(130, 198, 241 ,0.2) 1rpx solid ;
  padding: 20rpx;
}

.playlist-name {
  font-size: 28rpx;
  font-weight: 600;
  margin-bottom: 8rpx;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.playlist-count {
  font-size: 22rpx;
  color: #888;
}

/* 创建按钮 */
.create-music-btn {
  position: fixed;
  bottom: 145rpx;
  right: 40rpx;
  width: 110rpx;
  height: 110rpx;
  background: linear-gradient(135deg, #6e8efb, #a777e3);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 10rpx 24rpx rgba(110, 142, 251, 0.5);
}

.create-music-btn .icon-add {
  color: #fff;
  font-size: 44rpx;
}
/* 弹窗 */
.modal-content {
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
	bottom: env(safe-area-inset-bottom);
	background: #fff;
	width: 85%;
	border-radius: 30rpx 30rpx 30rpx 30rpx;
	margin: 0 auto;
	padding: 40rpx;
}
.modal-title {
	font-size: 32rpx;
	font-weight: bold;
}

.modal-close {
	font-size: 36rpx;
	color: #888;
}

.form-group {
	margin-top: 30rpx;
}

.form-label {
	font-size: 28rpx;
}

.form-input,
.form-textarea {
	width: 93%;
	border: 2rpx solid #ddd;
	border-radius: 15rpx;
	padding: 20rpx;
	margin-top: 10rpx;
	font-size: 28rpx;
}
/* 响应式布局 (小屏改2列，大屏改3列) */
@media screen and (min-width: 600px) {
  .playlist-grid {
    grid-template-columns: repeat(3, 1fr);
  }
}

@media screen and (min-width: 900px) {
  .playlist-grid {
    grid-template-columns: repeat(4, 1fr);
  }
}
</style>