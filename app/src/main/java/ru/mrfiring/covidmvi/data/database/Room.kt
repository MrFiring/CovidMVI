package ru.mrfiring.covidmvi.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface StatsDao {

    //Get queries
    @Query("select * from DatabaseGlobalStats limit 1")
    fun getGlobalStatsList(): Single<List<DatabaseGlobalStats>>

    @Query("select * from DatabaseGlobalHistoricalStats where resolution = :resolution")
    fun getGlobalHistoricalStatsByResolution(
        resolution: String
    ): Single<List<DatabaseGlobalHistoricalStats>>

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
    fun insertAllGlobalHistoricalStats(items: List<DatabaseGlobalHistoricalStats>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContinentStats(items: List<DatabaseContinentStats>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCountryHistoricalStats(items: List<DatabaseCountryHistoricalStats>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContinentCountry(items: List<DatabaseContinentCountry>): Completable
    //end insert queries

}


@Database(
    entities = [
        DatabaseGlobalStats::class,
        DatabaseContinentStats::class,
        DatabaseContinentCountry::class,
        DatabaseGlobalHistoricalStats::class,
        DatabaseCountryHistoricalStats::class
    ],
    version = 2
)
abstract class CovidDatabase : RoomDatabase() {
    abstract val statsDao: StatsDao
}