package com.jcardenas.domain.entities

import com.google.firebase.storage.StorageReference
import com.jcardenas.domain.common.ListItemViewModel

data class Image(
    val reference: StorageReference
): ListItemViewModel()