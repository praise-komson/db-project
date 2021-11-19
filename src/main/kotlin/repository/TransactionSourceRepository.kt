package repository

import db.DatabaseHelper

object TransactionSourceRepository {

    private val transactionSourceQueries = DatabaseHelper.database.transactionSourceQueries

    fun createTransactionSource(): Long {
        return transactionSourceQueries.transactionWithResult {
            transactionSourceQueries.createTransactionSource()
            return@transactionWithResult transactionSourceQueries.lastInsertRowId().executeAsOne()
        }
    }
}