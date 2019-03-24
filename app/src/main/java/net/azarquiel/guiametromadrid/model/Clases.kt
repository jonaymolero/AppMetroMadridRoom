package net.azarquiel.guiametromadrid.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "linea")
data class Linea(@PrimaryKey
                 @ColumnInfo(name = "id") // nombre en tabla
                 var id: Int=0,          // atributo en entity
                 @ColumnInfo(name = "nombre")
                 var nombre:String="",
                 @ColumnInfo(name = "color")
                 var color:String="")

@Entity(tableName = "estacion",
        foreignKeys = arrayOf(ForeignKey(entity = Linea::class,
                                        parentColumns = arrayOf("id"),
                                        childColumns = arrayOf("linea"))))
data class Estacion(@PrimaryKey
                    @ColumnInfo(name = "id") // nombre en tabla
                    var id: Int=0,          // atributo en entity
                    @ColumnInfo(name = "nombre")
                    var nombre:String="",
                    @ColumnInfo(name="linea")
                    var linea:Int=0)

data class LineaView(var id:Int=0,var nombre:String="",var color:String="",var inicial:String="",var ultima:String=""):Serializable
data class EstacionView(var id:Int=0,var nombre:String="",var lineas:List<Int>)


