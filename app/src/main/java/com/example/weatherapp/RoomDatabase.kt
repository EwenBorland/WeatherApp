package com.example.weatherapp
import androidx.room.*
import java.util.*

class Forecast(){

}

@Entity
data class Location(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "location_name") val locationName: String?,
    @ColumnInfo(name = "latitude_value") val latitude: Float?,
    @ColumnInfo(name = "longitude_value") val longitude: Float?,
    @ColumnInfo(name = "forecast_list") val forecasts: String
)

@Entity
data class Provider(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "provider_name") val providerName: String?,
    @ColumnInfo(name = "api_key") val apiKey: String?
)

@Dao
interface LocationDao {
    @Query("SELECT * FROM location")
    fun getAll(): List<Location>

    @Query("SELECT * FROM location WHERE uid IN (:locationIds)")
    fun loadAllByIds(locationIds: IntArray): List<Location>

    @Query("SELECT * FROM location WHERE location_name LIKE :name LIMIT 1")
    fun findByName(name: String): Location

    @Insert
    fun insertAll(vararg locations: Location)

    @Delete
    fun delete(location: Location)

    @Update
    fun updateLocations(vararg locations: Location)
}

@Dao
interface ProviderDao {
    @Query("SELECT * FROM provider")
    fun getAll(): List<Provider>

    @Query("SELECT * FROM provider WHERE uid IN (:providerIds)")
    fun loadAllByIds(providerIds: IntArray): List<Provider>

    @Query("SELECT * FROM provider WHERE provider_name LIKE :name LIMIT 1")
    fun findByName(name: String): Provider

    @Insert
    fun insertAll(vararg providers: Provider)

    @Delete
    fun delete(provider: Provider)

    @Update
    fun updateProvider(vararg providers: Provider)
}

@Database(entities = [Location::class, Provider::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun providerDao(): ProviderDao
}



