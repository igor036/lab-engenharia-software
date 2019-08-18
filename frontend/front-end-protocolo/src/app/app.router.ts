import { URLS_NAMES } from './app.constante';

//componentes
import { AppComponent } from './componentes/app/app.component';
import { HomeComponent } from './componentes/home/home.component';

//componentes docente
import { LoginComponent } from './docente/pagina/login/login.component';
import { PerfilComponent } from './docente/pagina/perfil/perfil.component';

export const ROTAS = [
    {path: '', component: AppComponent},
    {path: URLS_NAMES.home, component: HomeComponent},
    {path: URLS_NAMES.login, component: LoginComponent},
    {path: URLS_NAMES.perfil, component: PerfilComponent}
];