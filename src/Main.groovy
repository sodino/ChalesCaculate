import groovy.json.JsonSlurper

//def PATTERN_PAGE = /page=([0-9]*)&/

def baseDir = "./../res/"
//def fileJson = "kuaishou.hot.android.json"
def fileJson = "meipai.hot.android.json"

def inputFile = new File(baseDir + fileJson)

println("Page \t Duration \t DNS \t Connect \t SSL \t Request \t Response \t Latency \t ReqSize \t RespSize \t")

def page = 0
def calcDuration = new Calc("Duration")
def calcDns = new Calc("DNS")
def calcConnect = new Calc("Connect")
def calcSSL = new Calc("SSL")
def calcReq = new Calc("Request")
def calcResp = new Calc("Response")
def calcLatency = new Calc("Latency")
def calcReqSize = new Calc("RequestSize")
def calcRespSize = new Calc("ResponseSize")

new JsonSlurper().parseText(inputFile.text).each{
//    def matcher = (it.query =~ PATTERN_PAGE)
//    def page = 0
//    matcher.each { match, tmpPage ->
//        page = tmpPage
//    }
    page ++

    print(page + " \t\t ")
    calcDuration.calc(it.durations.total)
    calcDns.calc(it.durations.dns)
    calcConnect.calc(it.durations.connect)
    calcSSL.calc(it.durations.ssl)
    calcReq.calc(it.durations.request)
    calcResp.calc(it.durations.response)
    calcLatency.calc(it.durations.latency)
    calcReqSize.calc(it.request.sizes.headers + it.request.sizes.body)
    calcRespSize.calc(it.response.sizes.headers + it.response.sizes.body)
    println("")
}

println("\n\n")


calcDuration.printInfo()
calcDns.printInfo()
calcConnect.printInfo()
calcSSL.printInfo()
calcReq.printInfo()
calcResp.printInfo()
calcLatency.printInfo()
calcReqSize.printInfo()
calcRespSize.printInfo()
