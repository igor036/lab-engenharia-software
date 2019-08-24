/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { URLS_NAMES } from './app.constante';

//componentes
import { AppComponent } from './componentes/app/app.component';
import { HomeComponent } from './componentes/home/home.component';

//componentes docente
import { LoginComponent } from './docente/pagina/login/login.component';
import { PerfilComponent } from './docente/pagina/perfil/perfil.component';
import { CadastroComponent } from './docente/pagina/cadastro/cadastro.component';

export const ROTAS = [
    {path: '', component: AppComponent},
    {path: URLS_NAMES.home, component: HomeComponent},
    {path: URLS_NAMES.login, component: LoginComponent},
    {path: URLS_NAMES.perfil, component: PerfilComponent},
    {path: URLS_NAMES.cadastro, component: CadastroComponent}
];