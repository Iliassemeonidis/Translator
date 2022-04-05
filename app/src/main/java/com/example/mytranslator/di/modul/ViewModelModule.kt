package geekbrains.ru.translator.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytranslator.di.factory.ViewModelFactory
import com.example.mytranslator.di.factory.ViewModelKey
import com.example.mytranslator.di.modul.InteractorModule
import com.example.mytranslator.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}
