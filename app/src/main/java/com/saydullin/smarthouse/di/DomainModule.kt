package com.saydullin.smarthouse.di

import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.repository.ProfileRepository
import com.saydullin.smarthouse.domain.usecase.apartment.AddApartmentUseCase
import com.saydullin.smarthouse.domain.usecase.apartment.GetApartmentUseCase
import com.saydullin.smarthouse.domain.usecase.apartment.SaveApartmentFavouriteUseCase
import com.saydullin.smarthouse.domain.usecase.auth.LogOutUseCase
import com.saydullin.smarthouse.domain.usecase.auth.SignInUseCase
import com.saydullin.smarthouse.domain.usecase.auth.SignUpUseCase
import com.saydullin.smarthouse.domain.usecase.profile.GetProfileDataUseCase
import com.saydullin.smarthouse.domain.usecase.profile.SaveProfileDataUseCase
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

    @Provides
    fun providesLogOutUseCase(): LogOutUseCase {
        return LogOutUseCase()
    }

    @Provides
    fun providesGetApartmentsUseCase(
        apartmentsRepository: ApartmentsRepository
    ): GetApartmentUseCase {
        return GetApartmentUseCase(
            apartmentRepository = apartmentsRepository
        )
    }

    @Provides
    fun providesSaveApartmentFavouriteUseCase(
        apartmentsRepository: ApartmentsRepository
    ): SaveApartmentFavouriteUseCase {
        return SaveApartmentFavouriteUseCase(
            apartmentsRepository = apartmentsRepository
        )
    }

    @Provides
    fun providesAddApartmentUseCase(
        apartmentsRepository: ApartmentsRepository
    ): AddApartmentUseCase {
        return AddApartmentUseCase(
            apartmentRepository = apartmentsRepository
        )
    }

    @Provides
    fun providesGetProfileDataUseCase(
        profileRepository: ProfileRepository
    ): GetProfileDataUseCase {
        return GetProfileDataUseCase(
            profileRepository = profileRepository
        )
    }

    @Provides
    fun providesSaveProfileDataUseCase(
        profileRepository: ProfileRepository
    ): SaveProfileDataUseCase {
        return SaveProfileDataUseCase(
            profileRepository = profileRepository
        )
    }

}


