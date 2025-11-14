<template>
  <view class="page">
    <!-- 个人信息区域 -->
    <view class="profile-section">
      <view class="profile-header">
        <image class="avatar" :src="UserResStore.userRes.userHead" mode="aspectFill"></image>
        <view class="user-info">
          <text class="username">{{ UserResStore.userRes.username }}</text>
        </view>
      </view>
	  <!-- 数据统计展示区，暂时搁置 -->
<!--      <view class="stats">
        <view class="stat-item" v-for="(item, index) in stats" :key="index">
          <text class="stat-value">{{ item.value }}</text>
          <text class="stat-label">{{ item.label }}</text>
        </view>
      </view> -->
    </view>

    <!-- 歌单区域 -->
    <view class="playlist-section">
      <view class="section-title">
        <text>我的歌单</text>
        <text class="sub-title">共{{ playlists.length }}个</text>
      </view>
      <view class="playlist-grid">
        <view
          class="playlist-item"
          v-for="(playlist, index) in playlists"
          :key="index"
          @click="openPlaylist(playlist.name)"
        >
          <view class="playlist-cover-container">
            <image :src="playlist.cover" class="playlist-cover" mode="aspectFill"></image>
          </view>
          <view class="playlist-info">
            <text class="playlist-name">{{ playlist.name }}</text>
            <text class="playlist-count">{{ playlist.count }}首歌曲</text>
          </view>
        </view>
      </view>
    </view>

    <!-- 创建音乐按钮 -->
    <view class="create-music-btn" @click="createMusic">
      <text class="fa fa-plus"></text>
    </view>
  </view>
</template>

<script setup>


</script>

<style scoped>
.page {
  background-color: #f8f9fa;
  padding-bottom: 100rpx;
}

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
  bottom: 40rpx;
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

.create-music-btn text {
  color: #fff;
  font-size: 44rpx;
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