package com.jcardenas.data.mappers

import com.jcardenas.data.entities.UserEntity
import com.jcardenas.domain.entities.User

class UserEntityMapper {

    fun toUser(entity: UserEntity): User {
        return User(
            employeeNumber = entity.employeeNumber,
            fullName = entity.fullName,
            address = entity.address,
            phone = entity.phone,
            email = entity.email
        )
    }

    fun toUserEntity(user: User): UserEntity {
        return UserEntity(
            employeeNumber = user.employeeNumber,
            fullName = user.fullName,
            address = user.address,
            phone = user.phone,
            email = user.email
        )
    }
}