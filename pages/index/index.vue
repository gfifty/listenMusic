<template>
	<div class="app-container">
		<pageHead></pageHead>
		<!-- 分类 -->
		<section class="categories">
			<div class="category-item" v-for="cat in categories" :key="cat.id">
				<i :class="cat.icon + ' icon-category'"></i>
				<span>{{ cat.name }}</span>
			</div>
		</section>

		<!-- 推荐歌单 -->
		<section class="playlist">
			<h2 class="default-title">推荐歌单</h2>
			<div class="playlist-grid">
				<div class="playlist-card animate" v-for="(p,index) in albumList" :key="index"
					@click="openSinglist(p.singlistId)">
					<img :src="p.singlistHead" class="playlist-img" alt="cover">
					<div class="playlist-info">
						<div class="playlist-name">{{ p.singlistName }}</div>
						<div class="playlist-meta">
							<span>{{p.singlistTime.slice(0, 10)}}</span>
							<span>{{p.singlistLike}}</span>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- 单曲推荐 -->
		<div class="section-title">
			<h2 class="default-title">单曲推荐</h2>
		</div>

		<div class="song-list">
			<div class="song-item playing" v-for="(p,index) in originalList" :key="index" @click="jumpTo(p,index)">
				<img :src="p.cover" class="song-cover" alt="图片加载异常">
				<div class="song-info">
					<div class="song-title">{{p.musicName}}</div>
					<div class="song-artist">{{p.singerName}}</div>
				</div>
				<div class="song-actions">
					<div class="song-duration">{{p.musicDuration}}</div>
				</div>
			</div>
		</div>
		<miniPlayer></miniPlayer>
	</div>
</template>

<script setup>
	import {req} from "/hook/req.js"
	import miniPlayer from "@/components/miniPlayer/miniPlayer.vue"
	import {inject,ref} from "vue"
	import pageHead from "@/components/pageHead/pageHead.vue"
	import {usePlayerStore} from "@/store/player.js"
	import {useSearchresStore} from "/store/data.js"
	import {useUserResStore} from "../../store/logindata.js"

	//使用Pinia的state和actions
	const searchRESStore = useSearchresStore();
	const userResStore = useUserResStore();
	// 映射state
	const searchRES = searchRESStore.searchRes;
	const setSearchRES = searchRESStore.setSearchRES;

	const originalList = ref([])
	const $root = inject('$root')
	const albumList = ref([])
	const categories = ref([{
			id: 1,
			name: "推荐",
			icon: "fas fa-star"
		},
		{
			id: 2,
			name: "排行榜",
			icon: "fas fa-list-ol"
		},
		{
			id: 3,
			name: "歌手",
			icon: "fas fa-user"
		},
		{
			id: 4,
			name: "分类",
			icon: "fas fa-th-large"
		},
	]);
	/*推荐歌单*/
	req("http://localhost:8080/singlist/getHotList").then(result => {
		if (result.data.status == 200) {
			albumList.value = result.data.data
		}
	})

	function openSinglist(singlistId) {
		uni.navigateTo({
			url: `/pages/singlistDetail/singlistDetail?singlistId=${singlistId}`
		});
	}
	/*单曲推荐单*/
	req("http://localhost:8080/Music/hotMusic").then(result => {
		if (result.data.status == 200) {
			originalList.value = result.data.data
		}
	})

	function jumpTo(p,index){
		console.log(originalList.value);
		const playerStore =usePlayerStore()
		playerStore.setPlaylist(originalList.value,index)
		uni.navigateTo({
		        url: `/pages/play/play`,
		    });
	}
	const togglePlay = () => {
		isPlaying.value = !isPlaying.value;
	};
</script>

<style scoped>
	.app-container {
		font-family: Arial, sans-serif;
		background: linear-gradient(135deg, #87CEFA  0%, #00BFFF  50%, #40E0D0 100%);
		min-height: 100vh;

		/* 给底部播放器留空间 */
	}

	/* 标题区域 */
	.section-title {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin: 30px 0 20px;
	}

	.default-title {
		font-size: 20px;
		font-weight: 600;
		color: #5e3a78;
	}

	/* 分类 */
	.categories {
		display: flex;
		justify-content: space-around;
		padding: 20px 0;
		background: #fafafa;
	}

	.category-item {
		display: flex;
		flex-direction: column;
		align-items: center;
		font-size: 14px;
		color: #666;
	}

	.icon-category {
		font-size: 22px;
		margin-bottom: 5px;
		color: #9cdf7e;
	}

	/* 推荐歌单 */
	.playlist {
		padding: 15px;
	}

	.playlist h2 {
		font-size: 18px;
		margin-bottom: 10px;
	}

	.playlist-grid {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		gap: 15px;
	}

	.playlist-info {
		padding:8px;
	}

	.playlist-info h3 {
		font-size: 14px;
		margin-bottom: 4px;
	}

	.playlist-meta {
		display: flex;
		align-items: center;
		font-size: 12px;
		color: #999;
	}

	/* 歌单列表 */
	.playlist-grid {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		gap: 20px;
		margin-bottom: 30px;
	}

	.playlist-card {
		background: rgba(255, 255, 255, 0.7);
		border-radius: 15px;
		overflow: hidden;
		transition: all 0.3s ease;
		backdrop-filter: blur(10px);
		box-shadow: 0 6px 15px rgba(158, 223, 126, 0.3);
	}

	.playlist-card:hover {
		transform: translateY(-5px);
		box-shadow: 0 12px 20px rgba(158, 223, 126, 0.4);
	}

	.playlist-img {
		width: 100%;
		height: 350rpx;
		aspect-ratio: 1;
		object-fit: cover;
	}

	.playlist-info {
		padding: 12px;
	}

	.playlist-name {
		font-weight: 600;
		margin-bottom: 5px;
		font-size: 15px; 
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		color: #5e3a78;
	}

	.playlist-meta {
		color: #a67fb8;
		font-size: 12px;
		display: flex;
		justify-content: space-between;
	}

	.playlist-meta .playlist-i {
		color: #9cdf7e;
	}

	/* 单曲推荐列表 */
	.song-list {
		background: rgba(255, 255, 255, 0.7);
		border-radius: 15px;
		padding: 15px;
		margin-bottom: 30px;
		backdrop-filter: blur(10px);
		box-shadow: 0 6px 15px rgba(158, 223, 126, 0.3);
	}

	.song-item {
		display: flex;
		align-items: center;
		padding: 12px 10px;
		border-radius: 10px;
		transition: all 0.3s ease;
		cursor: pointer;
		margin-bottom: 8px;
	}

	.song-item:last-child {
		margin-bottom: 0;
	}

	.song-item:hover {
		background: rgba(156, 223, 126, 0.1);
	}

	.song-item.playing {
		background: rgba(156, 223, 126, 0.2);
	}

	.song-cover {
		width: 50px;
		height: 50px;
		border-radius: 8px;
		object-fit: cover;
		margin-right: 12px;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	}

	.song-info {
		flex: 1;
	}

	.song-title {
		width: 80%;
		font-weight: 600;
		font-size: 16px;
		margin-bottom: 4px;
		color: #5e3a78;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.song-artist {
		font-size: 13px;
		color: #a67fb8;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.song-actions {
		display: flex;
		gap: 15px;
		align-items: center;
	}

	.song-like {
		color: #a67fb8;
		transition: all 0.3s ease;
	}

	.song-like.active {
		color: #ff4d7c;
	}

	.song-duration {
		font-size: 14px;
		color: #a67fb8;
		min-width: 40px;
		text-align: right;
	}
</style>