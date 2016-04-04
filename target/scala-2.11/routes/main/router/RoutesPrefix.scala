
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/liyifan/Documents/2016 Spring/appcivist-mobilization/appcivist-mobilization/conf/routes
// @DATE:Mon Apr 04 15:34:17 PDT 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
