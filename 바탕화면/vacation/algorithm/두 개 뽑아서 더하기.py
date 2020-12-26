def solution(numbers):
    answer = []
    for i in range(len(numbers)-1):
        for j in range(i+1, len(numbers)):
            answer.append(numbers[i]+numbers[j])

    answer1 = list(set(answer))
    answer1.sort()

    return answer1
