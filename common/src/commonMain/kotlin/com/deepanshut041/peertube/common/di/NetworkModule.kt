package com.deepanshut041.peertube.common.di

import com.deepanshut041.peertube.common.local.adapter.InstanceLocalAdapter
import com.deepanshut041.peertube.common.local.adapter.InstanceLocalAdapterImpl
import com.deepanshut041.peertube.common.local.dao.InstanceDao
import com.deepanshut041.peertube.common.remote.adapter.InstanceRemoteAdapter
import com.deepanshut041.peertube.common.remote.adapter.InstanceRemoteAdapterImpl
import com.deepanshut041.peertube.common.remote.adapter.VideoRemoteAdapter
import com.deepanshut041.peertube.common.remote.adapter.VideoRemoteAdapterImpl
import com.deepanshut041.peertube.common.remote.endpoints.InstanceEndpoints
import com.deepanshut041.peertube.common.remote.endpoints.VideoEndpoints
import com.deepanshut041.peertube.common.service.repository.InstanceRepository
import com.deepanshut041.peertube.common.service.repository.InstanceRepositoryImpl
import com.deepanshut041.peertube.common.service.repository.VideoRepository
import com.deepanshut041.peertube.common.service.repository.VideoRepositoryImpl
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val CONTENT_TYPE = "Content-Type"
private const val APPLICATION_JSON = "application/json"
private const val ACCEPT_VERSION = "Accept-Version"
private const val AUTHORIZATION = "Authorization"

val networkModule = module {
    single {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    isLenient = true; ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY

            }
            install(DefaultRequest) {
                headers.append(CONTENT_TYPE, APPLICATION_JSON)
            }
        }
    }
}

val instanceModule = module {
    single { InstanceEndpoints(get()) }
    single { InstanceDao(get()) }
    single<InstanceRemoteAdapter> { InstanceRemoteAdapterImpl(get(), get()) }
    single<InstanceLocalAdapter> { InstanceLocalAdapterImpl(get()) }
    single<InstanceRepository> {
        InstanceRepositoryImpl(
            get(),
            get(),
            get(),
            get(named(DefaultDispatcher))
        )
    }
}

val videoModule = module {
    single { VideoEndpoints(get()) }
    single<VideoRemoteAdapter> { VideoRemoteAdapterImpl(get(), get()) }
    single<VideoRepository> { VideoRepositoryImpl(get(), get(named(DefaultDispatcher))) }
}