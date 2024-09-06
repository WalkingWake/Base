package dev.lazygarde.lazy.preference

sealed class StoreType {
    data object Boolean : StoreType()
    data object Float : StoreType()
    data object Int : StoreType()
    data object Long : StoreType()
    data object String : StoreType()
}