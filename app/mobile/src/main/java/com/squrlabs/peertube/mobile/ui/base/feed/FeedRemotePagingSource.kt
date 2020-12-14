package com.squrlabs.peertube.mobile.ui.base.feed

import android.util.Log
import androidx.paging.PagingSource
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.common.service.repository.VideoRepository

class FeedRemotePagingSource(
    private val backend: VideoRepository,
    private val count: Int = 20,
    private val sort: String? = null,
    private val filter: String? = null
    ) : PagingSource<Int, VideoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoModel> {
        return try {
            val response = backend.getVideos(count = count, start = params.key ?: 0, sort = sort, filter = filter)
            val data = response.data ?: emptyList()
            val nextKey = if(data.isNotEmpty()) (params.key ?: 0) + 20 else null
            LoadResult.Page(
                data = data,
                prevKey = null, // Only paging forward.
                nextKey = nextKey
            )
        } catch (e: Exception) {
            // Handle errors in this block
            LoadResult.Error(e)
        }
    }
}