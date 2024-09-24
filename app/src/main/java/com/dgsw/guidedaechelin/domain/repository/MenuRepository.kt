package com.dgsw.guidedaechelin.domain.repository

import com.dgsw.guidedaechelin.domain.model.MenuModel

interface MenuRepository {

    suspend fun getMenu(

        date : String

    ) : MenuModel



}