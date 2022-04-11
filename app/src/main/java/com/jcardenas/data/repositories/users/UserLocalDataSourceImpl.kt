package com.jcardenas.data.repositories.users

import com.jcardenas.data.db.UserDao
import com.jcardenas.data.mappers.UserEntityMapper
import com.jcardenas.domain.entities.User
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserEntityMapper
): UserLocalDataSource {
    override suspend fun insert(user: User) {
        userDao.insert(userMapper.toUserEntity(user))
    }

    override fun all(): Flow<List<User>> {
        val users = userDao.getUsers()
        return users.map { list ->
            list.map {
                userMapper.toUser(it)
            }
        }
    }

}