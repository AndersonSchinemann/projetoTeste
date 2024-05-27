describe('usuarioService', function() {
    beforeEach(module('ListarApp'));
//Obter Todos os Usuários:
    it('should be able to get all users', inject(function(usuarioService, $httpBackend) {
    // Expectativa de uma requisição GET para /api/usuarios e respondendo com uma lista de usuários
    $httpBackend.expectGET('/api/usuarios').respond(200, [
        { id: 1, nome: 'João', email: 'joao@email.com' },
        { id: 2, nome: 'Maria', email: 'maria@email.com' }
    ]);

    // Chama a função para obter todos os usuários e realiza as expectativas
    usuarioService.getAllUsuarios().then(function(usuarios) {
        expect(usuarios.length).toBe(2);
        expect(usuarios[0].nome).toBe('João');
        expect(usuarios[1].nome).toBe('Maria');
    });

    // Força o flush das requisições HTTP mockadas
    $httpBackend.flush();
}));
//Excluir um Usuário:
    it('should be able to delete a user', inject(function(usuarioService, $httpBackend) {
    // Expectativa de uma requisição DELETE para /api/usuarios/1 e respondendo com status 200
    $httpBackend.expectDELETE('/api/usuarios/1').respond(200);

    // Chama a função para excluir um usuário e realiza as expectativas
    usuarioService.deleteUsuario({ id: 1 }).then(function() {
        expect(true).toBe(true); // Sucesso na exclusão
    });

    // Força o flush das requisições HTTP mockadas
    $httpBackend.flush();
	}));
//Atualizar um Usuário:
it('should be able to update a user', inject(function(usuarioService, $httpBackend) {
    // Expectativa de uma requisição PUT para /api/usuarios/1 e respondendo com status 200
    $httpBackend.expectPUT('/api/usuarios/1', { id: 1, nome: 'João Silva', email: 'joaosilva@email.com' }).respond(200);

    // Chama a função para atualizar um usuário e realiza as expectativas
    usuarioService.atualizarUsuario({ id: 1, nome: 'João Silva', email: 'joaosilva@email.com' }).then(function() {
        expect(true).toBe(true); // Sucesso na atualização
    });

    // Força o flush das requisições HTTP mockadas
    $httpBackend.flush();
	}));
//Pesquisar Usuários:
	it('should be able to search users', inject(function(usuarioService, $httpBackend) {
    // Expectativa de uma requisição GET para /api/usuarios/pesquisa com parâmetro de consulta e respondendo com uma lista de usuários
    $httpBackend.expectGET('/api/usuarios/pesquisa', { params: { searchText: 'joao' } }).respond(200, [
        { id: 1, nome: 'João', email: 'joao@email.com' }
    ]);

    // Chama a função para pesquisar usuários e realiza as expectativas
    usuarioService.pesquisaUsuarios('joao').then(function(usuarios) {
        expect(usuarios.length).toBe(1);
        expect(usuarios[0].nome).toBe('João');
    });

    // Força o flush das requisições HTTP mockadas
    $httpBackend.flush();
	}));
});