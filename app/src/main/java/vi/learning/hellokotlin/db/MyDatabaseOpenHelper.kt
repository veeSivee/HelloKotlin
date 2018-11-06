package vi.learning.hellokotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by taufiqotulfaidah on 11/4/18.
 */
class MyDatabaseOpenHelper (ctx: Context, dbname: String, version: Int) : ManagedSQLiteOpenHelper(ctx,dbname, null, version) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context, dbname: String, version: Int): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext, dbname, version)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createTableFavoriteClub(db)
        createTableFavoriteMatch(db)
    }

    private fun createTableFavoriteClub(db: SQLiteDatabase?) {
        db?.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.TEAM_ID to TEXT + UNIQUE,
                Favorite.TEAM_NAME to TEXT,
                Favorite.TEAM_BADGE to TEXT)
    }

    private fun createTableFavoriteMatch(db: SQLiteDatabase?) {
        db?.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.EVENT_ID to TEXT + UNIQUE,
                FavoriteMatch.TEAM_HOME to TEXT,
                FavoriteMatch.TEAM_HOME_SCORE to TEXT,
                FavoriteMatch.TEAM_AWAY to TEXT,
                FavoriteMatch.TEAM_AWAY_SCORE to TEXT,
                FavoriteMatch.MATCH_DATE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE, true)
        db?.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext,"FavoriteTeam.db", 2)
