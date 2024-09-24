package com.dgsw.guidedaechelin.data.repository

import com.dgsw.guidedaechelin.data.mapper.toModel
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.repository.MenuRepository
import com.dgsw.guidedaechelin.remote.service.MenuService
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuService: MenuService
) : MenuRepository{
    override suspend fun getMenu(date: String): MenuModel {
        return menuService.getMenu(date).toModel()
    }



}