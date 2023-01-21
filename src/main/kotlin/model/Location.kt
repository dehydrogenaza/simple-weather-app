package model

import java.util.UUID

class Location(
    val description: String,
    val region: String,
    val country: String,
    val adminArea: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
) : JpaPersistable(UUID.randomUUID(), 0)




//id	Binary(16)	long	Primary Key
//description	VARCHAR(255)	String	not null
//region	VARCHAR(255)	String
//country	VARCHAR(255)	String	not null
//admin_area	VARCHAR(255)	String
//city	VARCHAR(255)	String	not null
//latitude	DOUBLE	double
//longitude	DOUBLE	double
//--> 1 to N dates		List<ForecastDates>

//@Entity
//class PhoneNumber(
//    @Column(nullable = false)
//    val number: String,
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Int?=null)