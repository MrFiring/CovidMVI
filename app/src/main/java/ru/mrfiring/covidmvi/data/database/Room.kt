package ru.mrfiring.covidmvi.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface StatsDao {

    //Get queries
    @Query("select * from DatabaseGlobalStats")
    fun getGlobalStats(): Single<DatabaseGlobalStats>

    @Query("select * from DatabaseContinentStats")
    fun getContinentStatsList(): Single<List<DatabaseContinentStats>>

    @Query("select * from DatabaseCountryHistoricalStats where countryName = :name")
    fun getContinentHistoricalStatsByName(name: String): Single<DatabaseCountryHistoricalStats>

    @Query("select * from DatabaseContinentCountry where continentName =:continent")
    fun getContinentCountryList(continent: String): Single<List<DatabaseContinentCountry>>

    //end get queries

    //Insert queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGlobalStats(item: DatabaseGlobalStats): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContinentStats(items: List<DatabaseContinentStats>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCountryHistoricalStats(items: List<DatabaseCountryHistoricalStats>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContinentCountry(items: DatabaseContinentCountry)
    //end insert queries

}


@Database(
    entities = [
        DatabaseGlobalStats::class,
        DatabaseContinentStats::class,
        DatabaseContinentCountry::class,
        DatabaseCountryHistoricalStats::class
    ],
    version = 1
)
abstract class CovidDatabase : RoomDatabase() {
    abstract val statsDao: StatsDao
}