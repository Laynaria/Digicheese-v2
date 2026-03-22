import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { fetchUsers, createUser } from '@/api/users.api'

export const getUsersQueryOptions = () => ({
    queryKey: ['users'] as const,
    queryFn: fetchUsers,
})

export function useUsers() {
    return useQuery(getUsersQueryOptions())
}

export function useCreateUser() {
    const queryClient = useQueryClient()

    return useMutation({
        mutationFn: createUser,
        onSuccess: () => {
            void queryClient.invalidateQueries({ queryKey: ['users'] })
        },
    })
}