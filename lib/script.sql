-- criando tabelas do banco de dados...
CREATE TABLE public.tbusuario (
    usucodigo serial4 NOT NULL,
    usunome varchar(50) NOT NULL,
    usuemail varchar(60) NOT NULL,
    ususenha varchar(200) NOT NULL,
    usuativo int2 NOT NULL DEFAULT 1,
    CONSTRAINT usuario_pkey PRIMARY KEY (usucodigo)
);

-- mais duas tabelas pra vincular usuario a aluno e usuario a professor

CREATE TABLE public.tbusuarioprofessor (
    usucodigo int NOT NULL,
    codigoprofessor int NOT NULL,
    CONSTRAINT usuarioprofessor_pkey PRIMARY KEY (usucodigo,codigoprofessor)
    --// FK DE tbusuarioprofessor PARA USUARIO
--// FK DE tbusuarioprofessor PARA PROFESSOR
);

-- somente na parte web
CREATE TABLE public.tbusuarioaluno (
    usucodigo int NOT NULL,
    codigoALUNO int NOT NULL,
    CONSTRAINT usuarioaluno_pkey PRIMARY KEY (usucodigo,codigoALUNO)
    --// FK DE tbusuarioprofessor PARA USUARIO
--// FK DE tbusuarioprofessor PARA ALUNO
);

create table public.tbescola( 
    codigo serial not null,
    nome varchar(200) not null,
    CONSTRAINT pk_tbescola
    PRIMARY KEY (codigo)
);

create table public.tbturma( 
    codigo serial not null,
    descricao varchar(200) not null,
    nivel varchar(200) not null,    
    periodo varchar(200) not null,    
    CONSTRAINT pk_tbturma PRIMARY KEY (codigo)
);

create table public.tbturmaescola( 
    codigoturma int not null,
    codigoescola  int not null,
    CONSTRAINT pk_tbturmaescola PRIMARY KEY (codigoturma, codigoescola)
);

create table public.tbprofessor( 
    codigo serial not null,
    nome varchar(200) not null,   
    CONSTRAINT pk_tbprofessor PRIMARY KEY (codigo)
);

create table public.tbaluno( 
    codigo serial not null,
    nome varchar(200) not null,   
    CONSTRAINT pk_tbaluno PRIMARY KEY (codigo)
);

-- aluno turma 
create table public.tbalunoturma( 
    codigoturma int not null,
    codigoaluno  int not null,
    CONSTRAINT pk_tbalunoturma PRIMARY KEY (codigoturma, codigoaluno)
);

--profesor turma 
create table public.tbprofessorturma( 
    codigoturma int not null,
    codigoprofessor  int not null,
    CONSTRAINT pk_tbprofessorturma PRIMARY KEY (codigoturma, codigoprofessor)
);

--profesor materia 
create table public.tbprofessormateria( 
    codigomateria int not null,
    codigoprofessor  int not null,
    CONSTRAINT pk_tbprofessormateria PRIMARY KEY (codigomateria, codigoprofessor)
);

create table public.tbmateria( 
    codigo serial not null,
    nome varchar(200) not null ,  
    CONSTRAINT pk_tbmateria PRIMARY KEY (codigo)
);

-- notas 
create table public.tbnota( 
    codigo serial not null,
    codigoprofessor int not null,
    codigomateria int not null,
    codigoaluno  int not null,  
    datanota date not null,
    nota numeric(10,2) not null,
    CONSTRAINT pk_tbnota PRIMARY KEY (codigo)
);

