# yuno-antifraud-riskified-android

La librería YunoRiskified es una herramienta que permite la integración con el servicio de detección
de fraudes de RiskifiedBeacon.

Para integrar esta librería en tu aplicación, sigue los siguientes pasos:

##Step 1.

Añade la dependencia de la librería YunoRiskified al archivo `build.gradle` de tu módulo de app.
Asegúrate de añadir la última versión disponible y configurar el repositorio Maven donde se
encuentra la librería. Para ello, agrega la siguiente línea al archivo `settings.gradle`

```Gradle
dependencyResolutionManagement {
    repositories {
        maven { url "https://yunopayments.jfrog.io/artifactory/snapshots-libs-release" }
        // Otros repositorios
        mavenCentral()
        google()
    }
}
```

Luego, en el archivo `build.gradle` de tu módulo de app, agrega la dependencia:

```Gradle
dependencies { implementation 'com.yuno.fraud-riskified:yunoriskified:0.1.0' }
```

##Step 2.

Inicializa la librería YunoRiskified en tu aplicación. Para ello, llama a la función
`initYunoRiskified` la cual recibe dos parametros `shopName` - Name of your Riskified account y `token` -
Auth Token en el método `onCreate` de tu clase Application:

```kotlin
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initYunoRiskified("shopName", "token")
    }
}
```

##Step 3.

Llama la función `onCreateYunoRiskified` en el método `onCreate` de la actividad principal, esto para
retornar el SessionId generado por el SDK:

```kotlin
class MainActivity : AppCompatActivity() {
    private var yunoSignifydSessionId: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retorna el sessionId 
        yunoRiskifiedSessionId = onCreateYunoRiskified()

    }
}
```

##Step 4.

Una vez que hayas obtenido el SessionId, puedes utilizarlo en el proceso de checkout llamando a la
función correspondiente. Asegúrate de pasar el SessionId como parámetro al método `startCheckout` en
el parámetro `merchantSessionId`. Esto permitirá que la librería YunoRiskified asocie el perfil de
dispositivo generado con la transacción de pago correspondiente.

```kotlin
override fun onResume() {
    super.onResume()
    // Generar perfil de dispositivo y enviar a Yuno 
    startCheckout(
        callbackPaymentState = this::onPaymentStateChange,
        merchantSessionId = yunoRiskifiedSessionId
    )
}
```