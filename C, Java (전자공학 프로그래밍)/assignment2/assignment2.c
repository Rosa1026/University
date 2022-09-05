/*
�а� : ���ڰ��а�
�й� : 201620837
�̸� : �ȹα�
���� : ���ڰ��� ���α׷���
������ : ������ ������
*/
#define _CRT_SECURE_NO_WARNINGS
#define MEMORY_SIZE 100
#define VARIABLE_LENGTH 10
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h> //exit()�Լ��� ����ϱ� ���� �ҷ���

void updateMemory(int menu);
void viewMemory();
void allocateVariable();
void deleteVariable(); //����� �Լ����� �̸� ����

int stack_pointer = 0, memory_address[MEMORY_SIZE] = { 0 }, array_pointer = 0, array_size[MEMORY_SIZE] = { 0 }; //stack �� ��ġ�� ��Ÿ���� stack_pointer, 0������ memory_size ��ŭ�� ���� ���� memory_address stack, ���Ŀ� �ԷµǴ� array_size�� �����ϱ� ���� ���ο� ���� ���� �� ��ġ�� �˷��ִ� array_pointer ����
char variable_table[MEMORY_SIZE][2][VARIABLE_LENGTH] = { 0 }; //0������ Memory_size������ ���� ������, ������ 2ĭ, �� ĭ �� variable_length�� �Է��� ���� �� �ִ� table ����

int main() {
	float check_num; //�޴� ������ ���� check_num�̶�� float�� ���� ����, ������ �ƴ� ���� �Էµ��� ���� Ȯ���ϱ� ���� float�� ������ ����
	int menu; //�Ŀ� updatememory ȣ���� ���� int�� ���� ����

	do {
		printf("1: exit\n");
		printf("2: memory view\n");
		printf("3: variable allocation\n");
		printf("4: variable delete\n");
		printf(">> select memu : "); //������ �޴� ����Ʈ

		if (scanf("%f", &check_num) != 1 || (int)check_num != check_num) {  
			while (getchar() != '\n');
			system("cls");
			printf("Please enter an integer\n\n");
			continue;
		} //�Էµ� ���� ������ �ƴ϶�� ������ �Է��ϰԲ� �ٽ� ����
		else if ((int)check_num < 1 || (int)check_num > 4) {
			system("cls");
			printf("Please enter the correct menu.\n\n");
			continue;
		} //�Էµ� ���ڰ� 1���� �۰ų� 4���� ũ�� �ǹٸ� �޴��� ������
		else if ((int)check_num == 1) {
			printf("\n\nExit the program.\n\n");
			break;
		} //�Էµ� ���ڰ� 1�̶�� ���α׷� ����

		system("cls"); //���μ����� ����&���&��ü�� ����� �ϴ� system �Լ� ȣ��
		menu = check_num; //menu�� �Էµ� check_num�� ���� ����/ �̹� ������ int������ ��ȯ�Ǿ� ���� ����
		printf("  selected menu : %d\n\n", menu); 
		updateMemory(menu); //menu�� updateMemory ȣ��

	} while (1); //do while ��

	return 0;
}

void updateMemory(int menu) {
	void (*function_pointer)(); //�������Լ� ����

	if (menu == 2) {
		function_pointer = viewMemory;
		function_pointer(); //���õ� �޴��� 2��� �Ҵ�� �޸𸮸� ���� ���� viewmemory �Լ�����
	}
	else if (menu == 3) {
		function_pointer = allocateVariable;
		function_pointer(); //���õ� �޴��� 3��� ������ �Ҵ��ϱ� ���� allocatevariable �Լ�����
	}
	else if (menu == 4) {
		function_pointer = deleteVariable;
		function_pointer(); //���õ� �޴��� 4��� �Ҵ�� ������ ����� ���� deletevariable �Լ�����
	}
}

void viewMemory() {
	printf("\nmemory address\n------\n");
	for (int n = 0; n < MEMORY_SIZE; n++) { //0���� memory_size���� Ž��
		printf("%3d |", n);
		if (memory_address[n] != 0) { //memory_address[n]�� 0�� �ƴҶ����� ���� -> ��, �޸𸮰� �Ҵ�Ǿ� ����
			printf(" } ");
			if (memory_address[n] == 2) //memory_address[n]�� 2��� ������ ó�� �Ҵ��� ��ġ�̹Ƿ� ������ �̸��� ���� �迭�� ����� ������ 
				printf("%s %s[%d]", variable_table[n][1], variable_table[n][2], array_size[n]); //�޸𸮰� ����ִ� n�� ã�Ƴ´ٸ�, �� �� variable_table�� n��° 1,2. ��, �Ҵ��� ������ ������ �̸��� ���/���� array_size�� ����� n��° ���ڸ� �Ҿ��
		}
		printf("\n");
	}
	printf("------\n\n\n");
}

void allocateVariable() {
	void (*function_pointer)();
	int variable_size = 0, size = 0; //������ ũ��� �迭�� ũ�⸦ �ޱ� ���� int�� ��������
	char variable_type[VARIABLE_LENGTH], variable_name[VARIABLE_LENGTH]; //������ �̸��� Ÿ���� �Է¹ޱ� ���� char�� ���� ����

	printf("variable_type : ");
	scanf("%s", variable_type);
	printf("variable_name : ");
	scanf("%s", variable_name);
	printf("array_size : ");
	scanf("%d", &array_size[array_pointer]); //������ �̸�, ����, �迭 ���� �Է¹���. �迭 ���� ���� ���������� ����� array_size[] stack�� ����

	size = array_size[array_pointer]; //�迭�� ����� ���� ���� size�� ����

	strcpy(variable_table[stack_pointer][1], variable_type);
	strcpy(variable_table[stack_pointer][2], variable_name); //�Է¹��� ������ �̸��� Ÿ���� variable_table�� stack_pointer�� 1,2ĭ�� ����


	if (strcmp(variable_type, "char") == 0)
		variable_size = 1;
	else if (strcmp(variable_type, "int") == 0)
		variable_size = 4;
	else if (strcmp(variable_type, "float") == 0)
		variable_size = 4;
	else if (strcmp(variable_type, "double") == 0)
		variable_size = 8; //�Է¹��� variable_type�� ���ڿ��� ���Ͽ� ������ ������ ���߾� variable_size�� ���� ����

	if (variable_size * size > MEMORY_SIZE)
	{
		printf("Variable size is too large.");
		exit(0);
	} //����ó�� (�޸��Ҵ� �Ұ�), �Է¹��� ������ size�� �迭�� ũ���� ���� ������ ������ memory_size���� ũ�ٸ� �Ҵ�Ұ� ����Ʈ�ϰ� ���α׷� ����

	if (MEMORY_SIZE - stack_pointer < (variable_size) * (array_size[array_pointer]))
	{
		for (int i = 0; i < MEMORY_SIZE; i++)
		{
			*variable_table[i][1] = 0;
			*variable_table[i][2] = 0;
			array_size[i] = 0;
			memory_address[i] = 0;
		} //�̹� ���ִ� variable_table, array_size, memory_address�� 0���� �ʱ�ȭ
		stack_pointer = 0;
		array_pointer = 0; //���� �����ؾ��ϹǷ� stack_pointer�� array_pointer�� 0���� �ʱ�ȭ
		strcpy(variable_table[stack_pointer][1], variable_type);
		strcpy(variable_table[stack_pointer][2], variable_name); //�ʱ�ȭ ���� �Էµ� ���ڿ��� �ٽ� �����ؼ� variable_table�� ����. �� �� stack_pointer�� 0�̹Ƿ� 0������ �ٽ� ����
		array_size[array_pointer] = size; //size�� �����س��� ���� �ʱ�ȭ�� array_pointer���� array_size�� �ٽ� ����. �̸� ���� size�� �̸� ���� ����.
	} //����ó�� (�ڸ� �� ������ ��), �Է��� ������ �Ҵ緮���� �޸𸮰� ���� ������ ���, �ʱ�ȭ �� 0������ �ٽ� ����.

	memory_address[stack_pointer] = 2; //stack_pointer���� memory stack���� 2�� ����. �Է¹��� ���̱� ������
	for (int n = 1; n < variable_size; n++)
		memory_address[stack_pointer + n] = 1; //�Է¹��� ������ ũ�⸸ŭ 1�� ����.

	stack_pointer += variable_size; //stack_pointer�� ������ ũ�⸸ŭ ����.

	for (int i = 1; i < size; i++)
	{
		for (int n = 0; n < variable_size; n++)
			memory_address[stack_pointer + n] = 1;
		stack_pointer += variable_size;
	} //�Էµ� �迭�� ũ�⸸ŭ 1�� ä���ִ� �ݺ���.

	array_pointer = stack_pointer; //array_pinter�� stack_pointer�� ���� ���� ����. �޸𸮸� �����ϴ� memory_address�� �迭�� ���� �����ϴ� array_size�� ���� ���� �ְ��ϱ� ����

	function_pointer = viewMemory;
	function_pointer(); //viewmemory �Լ� ȣ��
}

void deleteVariable() {
	void (*function_pointer)();
	int stack = 0; //�迭�� ����ŭ ����� ���� ��ŭ�� ������ �׿��ִ��� �˱����� ������ ����
	int variable_size = 0; //������ ũ�⸸ŭ �������ϹǷ� �̸� �˱����� ����
	char variable_name[VARIABLE_LENGTH]; //������ ������ �̸��� �ޱ� ���� char ���� ����

	printf("variable_name : ");
	scanf("%s", variable_name); //������ ���� �̸� �Է� ����

	for (int i = 0; i < MEMORY_SIZE; i++) { //0���� ���ϴ� ������ �������� Ž���� ��. �� �� Ž���� ��ġ���� i�� ��
		if (strcmp(variable_name, variable_table[i][2]) == 0) {
			if (strcmp(variable_table[i][1], "char") == 0)
				variable_size = 1;
			else if (strcmp(variable_table[i][1], "int") == 0)
				variable_size = 4;
			else if (strcmp(variable_table[i][1], "float") == 0)
				variable_size = 4;
			else if (strcmp(variable_table[i][1], "double") == 0)
				variable_size = 8; //�Էµ� ������ �̸��� ���ڿ��� ���� �׿� �´� ������ ũ�⸦ �Է���

			for (int n = 0; n < variable_size; n++)
				memory_address[i + n] = 0; //0���� ������ ũ����� ���ÿ� ����� ���� 0���� �ʱ�ȭ, ������ �� ������ ũ�⸸ŭ�� �ݺ�

			stack = i; //Ž���� ��ġ�� ���� stack�̶�� ������ ������. �Ŀ� �迭��ŭ ����� ���� ������

			for (int m = 1; m < array_size[i]; m++) //1���� �迭�� ũ�⸸ŭ �ݺ�
			{
				for (int n = 0; n < variable_size; n++) //�ռ� i���� ������ ũ�⸸ŭ �����ߴٸ� �̸� i�� ���� stack���� �־� stack�� ����� ���� ������ ũ�� �� n��ŭ ����
					memory_address[stack+variable_size + n] = 0; //�̴� �̹� �տ��� ������ ũ�⸸ŭ ������ �Ͽ����Ƿ� �� ������ ���� ���������
				stack += variable_size; //������ ũ�⸸ŭ ������ �����Ƿ� stack�� ������ ũ�⸦ ������ stack�� ����. ���� �迭��ŭ ���� �� ����
			}

			*variable_table[i][1] = 0;
			*variable_table[i][2] = 0; //variable_table �ʱ�ȭ
			array_size[i] = 0; //Ž���� ��ġ�� variable_table �� array_size 0���� �ʱ�ȭ
			break;
		}
	}

	function_pointer = viewMemory;
	function_pointer(); //viewmemory �Լ� ȣ��
}