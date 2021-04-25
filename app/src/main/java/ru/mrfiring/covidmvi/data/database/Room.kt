package ru.mrfiring.covidmvi.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface GlobalStatsDao : ContinentStatsDao {

    //Get queries
    @Query("select * from DatabaseGlobalStats limit 1")
    fun getGlobalStatsList(): Single<List<DatabaseGlobalStats>>

    @Query("select * from DatabaseGlobalHistoricalStats where resolution = :resolution")
    fun getGlobalHistoricalStatsByResolution(
        resolution: String
    ): Single<List<DatabaseGlobalHistoricalStats>>
    //End get queries

    //Insert queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGlobalStats(item: DatabaseGlobalStats): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGlobalHistoricalStats(items: List<DatabaseGlobalHistoricalStats>): Completable

    //End insert queries

}

@Dao
interface ContinentStatsDao {
    //Get queries
    @Query("select * from DatabaseContinentStats")
    fun getContinentStatsList(): Single<List<DatabaseContinentStats>>

    @Query("select * from DatabaseContinentCountry where continentName =:continent")
    fun getContinentCountryList(continent: String): Single<List<DatabaseContinentCountry>>
    //End get queries

    //Insert queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContinentStats(items: List<DatabaseContinentStats>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContinentCountry(items: List<DatabaseContinentCountry>): Completable
    //End insert queries
}

@Dao
interface CountryStatsDao{

    @Query("select * from DatabaseCountryHistoricalStats where countryName = :name")
    fun getCountryHistoricalStatsByName(name: String): Single<DatabaseCountryHistoricalStats>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCountryHistoricalStats(items: List<DatabaseCountryHistoricalStats>): Completable
}

@Database(
    entities = [
        DatabaseGlobalStats::class,
        DatabaseContinentStats::class,
        DatabaseContinentCountry::class,
        DatabaseGlobalHistoricalStats::class,
        DatabaseCountryHistoricalStats::class
    ],
    version = 3
)
abstract class CovidDatabase : RoomDatabase() {
    abstract val globalStatsDao: GlobalStatsDao
    abstract val continentStatsDao: ContinentStatsDao
    abstract val countryStatsDao: CountryStatsDao
}