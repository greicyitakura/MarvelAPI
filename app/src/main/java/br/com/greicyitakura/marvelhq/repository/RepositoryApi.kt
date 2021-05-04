package br.com.greicyitakura.marvelhq.repository

import br.com.greicyitakura.marvelhq.MD5.Companion.md5
import br.com.greicyitakura.marvelhq.network.EndPointApi
import br.com.greicyitakura.marvelhq.network.RetrofitInit
import com.example.marvelhq.model.ComicResponse

class RepositoryApi {

    private var url = "https://gateway.marvel.com/v1/public/"

    private val PUBLIC_KEY : String? = "61f6ddc80f1fa3ff4747dfadd2547654"
    private val PRIVATE_KEY: String? ="b14a4441f23fd4c64e944a8b95b6f6a7660e852a"
    private val orderBy: String? = "-onsaleDate"
    private val format: String? = "comic"
    private val limit: Int? = 50

    var ts: String? = java.lang.Long.toString(System.currentTimeMillis() / 1000)
    var hash: String? = md5(ts + PRIVATE_KEY + PUBLIC_KEY)

    private var service = EndPointApi::class
    private val serviceMarvel = RetrofitInit(url).create(service)

    suspend fun getComicsService(page: Int = 0): ComicResponse {
        return serviceMarvel.getResponseComics(page, limit, format, orderBy, ts, hash, PUBLIC_KEY)
    }

    suspend fun getComicByIdService(comicId: Int): ComicResponse {
        return serviceMarvel.getResponseComicsById(comicId, orderBy, ts, hash, PUBLIC_KEY)
    }
}