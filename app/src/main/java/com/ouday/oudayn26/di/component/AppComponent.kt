import android.app.Application
import com.ouday.bitcoin.di.TransactionDomainModule
import com.ouday.bitcoin.di.TransactionPresentationModule
import com.ouday.bitcoin.di.TransactionRemoteModule
import com.ouday.oudayn26.App
import com.ouday.core.di.modules.ContextModule
import com.ouday.core.di.modules.CoroutinesThreadsProvider
import com.ouday.core.di.modules.NetworkModule
import com.ouday.core.di.modules.SchedulersModule
import com.ouday.oudayn26.di.builder.ActivityBuilder
import com.ouday.oudayn26.di.builder.FragmentBuilder
import com.ouday.oudayn26.di.ioc.ProfileIocProvider
import com.ouday.profile.di.ProfileDomainModule
import com.ouday.profile.di.ProfilePresentationModule
import com.ouday.profile.di.ProfileRemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        NetworkModule::class, ContextModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        SchedulersModule::class,
        CoroutinesThreadsProvider::class,
        TransactionRemoteModule::class,
        TransactionPresentationModule::class,
        TransactionDomainModule::class,
        ProfileRemoteModule::class,
        ProfilePresentationModule::class,
        ProfileDomainModule::class,
        ProfileIocProvider::class
    ]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}