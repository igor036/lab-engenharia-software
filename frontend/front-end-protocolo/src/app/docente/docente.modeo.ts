/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
interface Login {
    email: string,
    senha: string
}

interface DocenteLogado {
    matricula: number,
    nome: string,
    email: string,
    perfil: string,
}

export {
    Login,
    DocenteLogado
}