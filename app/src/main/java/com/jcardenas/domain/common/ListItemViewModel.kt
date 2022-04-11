package com.jcardenas.domain.common

import com.jcardenas.presentation.helpers.GenericAdapter

abstract class ListItemViewModel{
    var adapterPosition: Int = -1
    var onListItemViewClickListener: GenericAdapter.OnListItemViewClickListener? = null
}