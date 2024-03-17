package com.saydullin.smarthouse.di

import com.saydullin.smarthouse.domain.usecase.auth.SignInUseCase
import com.saydullin.smarthouse.domain.usecase.auth.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providesSignInUseCase(): SignInUseCase {
        return SignInUseCase()
    }

    @Provides
    fun providesSignUpUseCase(): SignUpUseCase {
        return SignUpUseCase()
    }

}


