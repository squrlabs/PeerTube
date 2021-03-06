package com.deepanshut041.peertube.common.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        networkModule,
        coroutinesModule,
        databaseModule,
        instanceModule,
        videoModule
    )
}

//used by ios etc.
fun initKoin() = initKoin {}